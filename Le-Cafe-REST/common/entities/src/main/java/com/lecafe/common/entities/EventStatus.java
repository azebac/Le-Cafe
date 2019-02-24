package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="event_status")
public class EventStatus extends BaseEntity
{
    @Column(name="name")
    private String name;

    @OneToMany( mappedBy = "fk_status", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Event> eventList;


    public EventStatus(long id)
    {
        super(id);
    }

    public EventStatus()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Event> getEventList()
    {
        return eventList;
    }

    public void setEventList(List<Event> eventList)
    {
        this.eventList = eventList;
    }
}
