package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Reservation")
public class Reservation extends BaseEntity
{
    @ManyToOne
    @JoinColumn( name = "fk_user" )
    private User user;

    @ManyToOne
    @JoinColumn( name = "fk_table" )
    private TableEntity table;

    @Column(name="date")
    private Date date;

    public Reservation()
    {
    }

    public Reservation(long id)
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

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
