package org.gogroup.game.infrastructure.mjankoo.subscriber;

import org.gogroup.game.domain.event.ClientMovedEvent;
import org.gogroup.game.domain.notification.ClientMovedNotification;
import org.gogroup.game.domain.notification.ClientNotifier;
import org.gogroup.shared.readModel.GameModel;
import org.mjankoo.framework.eventDispatcher.EventSubscriber.EventSubscriberInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ClientMovedSubscriber implements EventSubscriberInterface
{
    private final ClientNotifier clientNotifier;

    public ClientMovedSubscriber(ClientNotifier clientNotifier) {
        this.clientNotifier = clientNotifier;
    }

    @Override
    public HashMap<Class<?>, ArrayList<Object>> getSubscribedEvents() {
        return new HashMap<>() {{
            put(ClientMovedEvent.class, new ArrayList<>(
                    Arrays.asList("notifyOpponent", 0)
            ));
        }};
    }

    public void notifyOpponent(ClientMovedEvent event) {
        GameModel game = event.getGameModel();
        String opponentId = game.getClientId().equals(game.getGuestId()) ? game.getHostId() : game.getGuestId();
        if (opponentId.equals("BOT")) {
            return;
        }
        this.clientNotifier.notify(opponentId, new ClientMovedNotification());
    }
}
