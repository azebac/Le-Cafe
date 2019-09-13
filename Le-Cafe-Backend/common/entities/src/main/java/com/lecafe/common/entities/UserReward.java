package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_reward")
public class UserReward extends BaseEntity
{
    @ManyToOne
    @JoinColumn( name = "fk_user" )
    private User user;

    @ManyToOne
    @JoinColumn( name = "fk_reward" )
    private Reward reward;

    @Column(name="used")
    private byte used;

    @OneToMany( mappedBy = "userReward", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<AppliedDiscount> appliedDiscountList;

    public UserReward()
    {
    }

    public UserReward(long id)
    {
        super(id);
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Reward getReward()
    {
        return reward;
    }

    public void setReward(Reward reward)
    {
        this.reward = reward;
    }

    public byte getUsed()
    {
        return used;
    }

    public void setUsed(byte used)
    {
        this.used = used;
    }

    public List<AppliedDiscount> getAppliedDiscountList()
    {
        return appliedDiscountList;
    }

    public void setAppliedDiscountList(List<AppliedDiscount> appliedDiscountList)
    {
        this.appliedDiscountList = appliedDiscountList;
    }
}
