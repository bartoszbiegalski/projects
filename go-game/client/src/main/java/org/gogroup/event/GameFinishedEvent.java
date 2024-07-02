package org.gogroup.event;

import org.mjankoo.framework.eventDispatcher.EventSubscriber.EventInterface;

public class GameFinishedEvent implements EventInterface
{
    private Integer hostPoints;
    private Integer guestpoints;

    public GameFinishedEvent(Integer hostPoints, Integer guestpoints) {
        this.hostPoints = hostPoints;
        this.guestpoints = guestpoints;
    }

    public Integer getHostPoints() {
        return hostPoints;
    }

    public Integer getGuestpoints() {
        return guestpoints;
    }
}
