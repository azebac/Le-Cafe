package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Reward")
public class Reward extends BaseEntity {

    @Column(name="points")
    private int points;

    @Column(name="discount", precision = 5,scale = 2)
    private float discount;

    @Column(name="description")
    private String description;

    @OneToMany( mappedBy = "reward", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<UserReward> userRewardList;

    @OneToMany( mappedBy = "reward", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<EventReward> eventRewardList;

    /**
     * Method that initializes the Reward class
     * @param id Id of the object
     */
    public Reward(long id ){super(id);}

    /**
     * Default constructor for the Reward class
     */
    public Reward(){};

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<UserReward> getUserRewardList()
    {
        return userRewardList;
    }

    public void setUserRewardList(List<UserReward> userRewardList)
    {
        this.userRewardList = userRewardList;
    }

    public List<EventReward> getEventRewardList()
    {
        return eventRewardList;
    }

    public void setEventRewardList(List<EventReward> eventRewardList)
    {
        this.eventRewardList = eventRewardList;
    }
}
