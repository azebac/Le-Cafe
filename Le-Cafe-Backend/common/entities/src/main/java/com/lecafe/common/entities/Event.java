package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "Event" )
public class Event extends BaseEntity
{
    @Column(name="name")
    private String name;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Column(name="challenge")
    private byte challenge;

    @ManyToOne
    @JoinColumn( name = "fk_status", nullable = false )
    private EventStatus eventStatus;

    @OneToMany( mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<EventReward> eventRewardList;

    @OneToMany( mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<EventCondition> eventConditionList;


    public Event(long id)
    {
        super(id);
    }

    public Event()
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

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public byte getChallenge()
    {
        return challenge;
    }

    public void setChallenge(byte challenge)
    {
        this.challenge = challenge;
    }

    public List<EventReward> getEventRewardList()
    {
        return eventRewardList;
    }

    public void setEventRewardList(List<EventReward> eventRewardList)
    {
        this.eventRewardList = eventRewardList;
    }

    public List<EventCondition> getEventConditionList()
    {
        return eventConditionList;
    }

    public void setEventConditionList(List<EventCondition> eventConditionList)
    {
        this.eventConditionList = eventConditionList;
    }

    public EventStatus getEventStatus()
    {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus)
    {
        this.eventStatus = eventStatus;
    }
}
