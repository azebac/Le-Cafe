package com.lecafe.common.entities;

import javax.persistence.*;

@Entity
@Table(name="condition_dish")
public class ConditionDish extends BaseEntity
{
    @ManyToOne
    @JoinColumn( name = "fk_condition", nullable = false )
    private Condition condition;

    @ManyToOne
    @JoinColumn( name = "fk_dish", nullable = false )
    private Dish dish;

    @Column(name="amount")
    private int amount;

    public ConditionDish()
    {
    }

    public ConditionDish(long id)
    {
        super(id);
    }


    public Dish getDish()
    {
        return dish;
    }

    public Condition getCondition()
    {
        return condition;
    }

    public void setCondition(Condition condition)
    {
        this.condition = condition;
    }

    public void setDish(Dish dish)
    {
        this.dish = dish;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}

