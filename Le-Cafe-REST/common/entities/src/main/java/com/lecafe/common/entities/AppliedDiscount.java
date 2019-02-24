package com.lecafe.common.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="applied_discount")
public class AppliedDiscount extends BaseEntity
{
    @ManyToOne
    @JoinColumn( name = "fk_user_reward" )
    private UserReward userReward;

    @ManyToOne
    @JoinColumn( name = "fk_bill" )
    private Bill bill;

    public AppliedDiscount()
    {
    }

    public AppliedDiscount(long id)
    {
        super(id);
    }

    public UserReward getUserReward()
    {
        return userReward;
    }

    public void setUserReward(UserReward userReward)
    {
        this.userReward = userReward;
    }

    public Bill getBill()
    {
        return bill;
    }

    public void setBill(Bill bill)
    {
        this.bill = bill;
    }
}

