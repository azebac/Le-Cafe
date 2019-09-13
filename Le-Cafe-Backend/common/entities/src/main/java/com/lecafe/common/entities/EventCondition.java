package com.lecafe.common.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="event_condition")
public class EventCondition extends BaseEntity
{
    @ManyToOne
    @JoinColumn( name = "fk_event", nullable = false )
    private Event event;

    @ManyToOne
    @JoinColumn( name = "fk_condition", nullable = false )
    private Condition condition;

    public EventCondition()
    {
    }

    public EventCondition(long id)
    {
        super(id);
    }

    public Event getEvent()
    {
        return event;
    }

    public void setEvent(Event event)
    {
        this.event = event;
    }

    public Condition getCondition()
    {
        return condition;
    }

    public void setCondition(Condition condition)
    {
        this.condition = condition;
    }
}
