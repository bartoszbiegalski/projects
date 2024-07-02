package org.gogroup.game.domain.notification;

public interface ClientNotifier {
    void notify(String opponentId, AbstractNotification notification);
}
