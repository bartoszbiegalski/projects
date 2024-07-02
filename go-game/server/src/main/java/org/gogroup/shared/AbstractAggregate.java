package org.gogroup.shared;

import org.mjankoo.framework.eventDispatcher.EventDispatcherInterface;

import java.util.ArrayList;

public abstract class AbstractAggregate {
    private final ArrayList<AbstractDomainEvent> events = new ArrayList<>();
    public void recordThat(AbstractDomainEvent event)
    {
        this.events.add(event);
    }

    public void dispatchDomainEvents(EventDispatcherInterface eventDispatcher)
    {
        this.events.forEach(eventDispatcher::dispatch);
        this.events.clear();
    }
}
