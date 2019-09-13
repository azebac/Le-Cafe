package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="dish_status")
public class DishStatus extends BaseEntity
{
    @Column(name="name")
    private String name;


    @OneToMany( mappedBy = "status", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Dish> dishList;

    public DishStatus()
    {
    }

    public DishStatus(long id)
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

    public List<Dish> getDishList()
    {
        return dishList;
    }

    public void setDishList(List<Dish> dishList)
    {
        this.dishList = dishList;
    }
}
