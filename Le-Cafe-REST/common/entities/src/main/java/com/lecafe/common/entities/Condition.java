package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Condition")
public class Condition extends BaseEntity
{
    @Column(name="description")
    private String description;

    @Column(name="amount")
    private int amount;


    @OneToMany( mappedBy = "condition", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<EventCondition> eventConditionList;

    @OneToMany( mappedBy = "condition", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<ConditionDish> conditionDishList;

    public Condition()
    {
    }

    public Condition(long id)
    {
        super(id);
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public List<EventCondition> getEventConditionList()
    {
        return eventConditionList;
    }

    public void setEventConditionList(List<EventCondition> eventConditionList)
    {
        this.eventConditionList = eventConditionList;
    }

    public List<ConditionDish> getConditionDishList()
    {
        return conditionDishList;
    }

    public void setConditionDishList(List<ConditionDish> conditionDishList)
    {
        this.conditionDishList = conditionDishList;
    }
}
