package org.gogroup.game.domain.notification;

public class ClientSkippedTurnNotification extends AbstractNotification
{
    private final String notificationName = "client-skipped-turn";

    public String getNotificationName()
    {
        return this.notificationName;
    }
}
