package com.lecafe.common.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bill_dish")
public class BillDish extends BaseEntity
{
    @ManyToOne
    @JoinColumn( name = "fk_dish", nullable = false )
    private Dish dish;

    @ManyToOne
    @JoinColumn( name = "fk_bill", nullable = false )
    private Bill bill;

    public BillDish()
    {
    }

    public BillDish(long id)
    {
        super(id);
    }

    public Dish getDish()
    {
        return dish;
    }

    public void setDish(Dish dish)
    {
        this.dish = dish;
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
