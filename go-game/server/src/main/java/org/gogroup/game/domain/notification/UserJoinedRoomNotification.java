package org.gogroup.game.domain.notification;

public class UserJoinedRoomNotification extends AbstractNotification
{
    private final String notificationName = "client-joined";

    public String getNotificationName()
    {
        return this.notificationName;
    }
}
