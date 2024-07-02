package org.gogroup.game.domain.notification;

public class GameFinishedNotification extends AbstractNotification
{
    private final String notificationName = "game-finished";
    private final Integer hostPoints;

    private final Integer guestPoints;

    public GameFinishedNotification(Integer hostPoints, Integer guestPoints) {
        this.hostPoints = hostPoints;
        this.guestPoints = guestPoints;
    }

    public String getNotificationName()
    {
        return this.notificationName;
    }

    public Integer getHostPoints() {
        return hostPoints;
    }

    public Integer getGuestPoints() {
        return guestPoints;
    }
}
