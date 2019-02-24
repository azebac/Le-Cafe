package com.lecafe.common.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_table_bill")
public class UserTableBill extends BaseEntity
{
    @ManyToOne
    @JoinColumn( name = "fk_user" )
    private User user;

    @ManyToOne
    @JoinColumn( name = "fk_table" )
    private TableEntity table;

    @ManyToOne
    @JoinColumn( name = "fk_bill" )
    private Bill bill;

    public UserTableBill()
    {
    }

    public UserTableBill(long id)
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

    public TableEntity getTable()
    {
        return table;
    }

    public void setTable(TableEntity table)
    {
        this.table = table;
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
