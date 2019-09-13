package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Dish")
public class Dish extends BaseEntity
{
    @Column(name="name")
    private String name;

    @Column(name="price", precision = 20, scale = 2)
    private Float price;

    @Column(name="description")
    private String description;

    @Column(name="points")
    private int points;

    @OneToMany( mappedBy = "dish", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<ConditionDish> conditionDishList;

    @ManyToOne
    @JoinColumn( name = "fk_status", nullable = false )
    private DishStatus status;

    public Dish()
    {
    }

    public Dish(long id)
    {
        super(id);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Float getPrice()
    {
        return price;
    }

    public void setPrice(Float price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public List<ConditionDish> getConditionDishList()
    {
        return conditionDishList;
    }

    public void setConditionDishList(List<ConditionDish> conditionDishList)
    {
        this.conditionDishList = conditionDishList;
    }

    public DishStatus getStatus()
    {
        return status;
    }

    public void setStatus(DishStatus status)
    {
        this.status = status;
    }
}
