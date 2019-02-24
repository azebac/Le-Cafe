package com.lecafe.common.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="event_reward")
public class EventReward extends BaseEntity
{
    @ManyToOne
    @JoinColumn( name = "fk_event" )
    private Event event;

    @ManyToOne
    @JoinColumn( name = "fk_reward" )
    private Reward reward;

    public EventReward()
    {
    }

    public EventReward(long id)
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

    public Reward getReward()
    {
        return reward;
    }

    public void setReward(Reward reward)
    {
        this.reward = reward;
    }
}
