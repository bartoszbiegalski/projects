package org.gogroup.server.notificationReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gogroup.event.BoardChangedEvent;
import org.gogroup.event.GameFinishedEvent;
import org.gogroup.server.Client;
import org.mjankoo.framework.eventDispatcher.EventDispatcherInterface;
import org.mjankoo.framework.requestHandler.Exception.RequestFormatException;

import java.util.HashMap;
import java.util.Map;

public class NotificationReaderWorker implements Runnable
{
    private final Client client;

    private final EventDispatcherInterface eventDispatcher;

    public NotificationReaderWorker(Client client, EventDispatcherInterface eventDispatcher)
    {
        this.client = client;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(this.client.isWaitingForResponse()) {
                continue;
            }

            String notification = this.client.read();
            if (notification != null) {
                String notificationName = this.getNotificationName(notification);
                System.out.println(notificationName);
                if (notificationName.equals("client-joined") || notificationName.equals("client-moved") || notificationName.equals("client-skipped-turn")) {
                    this.eventDispatcher.dispatch(new BoardChangedEvent());
                }

                if (notificationName.equals("game-finished")) {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> notificationMap;
                    try {
                        notificationMap = mapper.readValue(notification, Map.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException();
                    }
                    this.eventDispatcher.dispatch(new GameFinishedEvent(
                            (Integer) notificationMap.getOrDefault("hostPoints", 0),
                            (Integer) notificationMap.getOrDefault("guestpoints", 0)
                    ));
                }
            }
        }
    }

    private String getNotificationName(String notification) {
        Map<String,Object> requestMap;
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestMap = mapper.readValue(notification, Map.class);
        } catch (JsonProcessingException e) {
            return "";
        }
        return (String) requestMap.getOrDefault("notificationName", "");
    }
}
