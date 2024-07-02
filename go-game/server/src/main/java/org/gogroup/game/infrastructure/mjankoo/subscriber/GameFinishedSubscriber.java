package org.gogroup.game.infrastructure.mjankoo.subscriber;

import org.gogroup.game.domain.event.GameFinishedEvent;
import org.gogroup.game.domain.notification.ClientNotifier;
import org.gogroup.game.domain.notification.GameFinishedNotification;
import org.gogroup.shared.readModel.GameModel;
import org.mjankoo.framework.eventDispatcher.EventSubscriber.EventSubscriberInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameFinishedSubscriber implements EventSubscriberInterface
{
    private final ClientNotifier clientNotifier;

    public GameFinishedSubscriber(ClientNotifier clientNotifier) {
        this.clientNotifier = clientNotifier;
    }

    @Override
    public HashMap<Class<?>, ArrayList<Object>> getSubscribedEvents() {
        return new HashMap<>() {{
            put(GameFinishedEvent.class, new ArrayList<>(
                    Arrays.asList("notifyClients", 0)
            ));
        }};
    }

    public void notifyClients(GameFinishedEvent event) {
        GameModel game = event.getGameModel();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        clientNotifier.notify(game.getHostId(), new GameFinishedNotification(event.getHostPoints(), event.getGuestPoints()));
                        clientNotifier.notify(game.getGuestId(), new GameFinishedNotification(event.getHostPoints(), event.getGuestPoints()));
                    }
                },
                1000
        );
    }
}
