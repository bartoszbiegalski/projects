package org.gogroup.game.domain.notification;

public class ClientMovedNotification extends AbstractNotification
{
    private final String notificationName = "client-moved";

    public String getNotificationName()
    {
        return this.notificationName;
    }
}
