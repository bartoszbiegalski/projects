package org.gogroup.game.infrastructure.mjankoo.subscriber;

import org.gogroup.game.domain.event.ClientJoinedGameEvent;
import org.gogroup.game.domain.notification.ClientNotifier;
import org.gogroup.game.domain.notification.UserJoinedRoomNotification;
import org.mjankoo.framework.eventDispatcher.EventSubscriber.EventSubscriberInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ClientJoinedGameSubscriber implements EventSubscriberInterface
{
    private final ClientNotifier clientNotifier;

    public ClientJoinedGameSubscriber(ClientNotifier clientNotifier)
    {
        this.clientNotifier = clientNotifier;
    }

    @Override
    public HashMap<Class<?>, ArrayList<Object>> getSubscribedEvents()
    {
        return new HashMap<>() {{
            put(ClientJoinedGameEvent.class, new ArrayList<>(
                    Arrays.asList("notifyOpponent", 0)
            ));
        }};
    }

    public void notifyOpponent(ClientJoinedGameEvent event) throws InterruptedException {
        if (event.getClientId().equals("BOT")) {
            return;
        }
        this.clientNotifier.notify(
                event.getHostId(),
                new UserJoinedRoomNotification()
        );
    }
}
