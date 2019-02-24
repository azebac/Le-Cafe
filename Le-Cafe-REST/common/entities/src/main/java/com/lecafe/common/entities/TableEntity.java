package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Table")
public class TableEntity extends BaseEntity
{
    @Column(name="number")
    private int number;

    @Column(name="active")
    private byte active;

    @OneToMany( mappedBy = "table", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Reservation> tableReservationList;

    @OneToMany( mappedBy = "table", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<UserTableBill> userBillTableList;

    public TableEntity()
    {
    }

    public TableEntity(long id)
    {
        super(id);
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public byte getActive()
    {
        return active;
    }

    public void setActive(byte active)
    {
        this.active = active;
    }

    public List<Reservation> getTableReservationList()
    {
        return tableReservationList;
    }

    public void setTableReservationList(List<Reservation> tableReservationList)
    {
        this.tableReservationList = tableReservationList;
    }

    public List<UserTableBill> getUserBillTableList()
    {
        return userBillTableList;
    }

    public void setUserBillTableList(List<UserTableBill> userBillTableList)
    {
        this.userBillTableList = userBillTableList;
    }
}



