package org.gogroup.game.infrastructure.mjankoo.subscriber;

import org.gogroup.game.domain.event.ClientSkippedTurnEvent;
import org.gogroup.game.domain.notification.ClientSkippedTurnNotification;
import org.gogroup.game.domain.notification.ClientNotifier;
import org.gogroup.shared.readModel.GameModel;
import org.mjankoo.framework.eventDispatcher.EventSubscriber.EventSubscriberInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ClientSkippedTurnSubscriber implements EventSubscriberInterface
{
    private final ClientNotifier clientNotifier;

    public ClientSkippedTurnSubscriber(ClientNotifier clientNotifier) {
        this.clientNotifier = clientNotifier;
    }

    @Override
    public HashMap<Class<?>, ArrayList<Object>> getSubscribedEvents() {
        return new HashMap<>() {{
            put(ClientSkippedTurnEvent.class, new ArrayList<>(
                    Arrays.asList("notifyOpponent", 0)
            ));
        }};
    }

    public void notifyOpponent(ClientSkippedTurnEvent event) {
        GameModel game = event.getGameModel();
        String opponentId = game.getClientId().equals(game.getGuestId()) ? game.getHostId() : game.getGuestId();
        if (opponentId.equals("BOT")) {
            return;
        }

        this.clientNotifier.notify(opponentId, new ClientSkippedTurnNotification());
    }
}
