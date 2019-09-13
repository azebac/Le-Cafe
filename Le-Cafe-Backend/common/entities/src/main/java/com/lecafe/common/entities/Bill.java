package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Bill")
public class Bill extends BaseEntity
{
    @Column(name="total", precision = 20, scale = 2)
    private float total;

    @OneToMany( mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<UserTableBill> userBillTableList;

    @OneToMany( mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<AppliedDiscount> appliedDiscountList;



    public Bill()
    {
    }

    public Bill(long id)
    {
        super(id);
    }

    public float getTotal()
    {
        return total;
    }

    public void setTotal(float total)
    {
        this.total = total;
    }

    public List<UserTableBill> getUserBillTableList()
    {
        return userBillTableList;
    }

    public void setUserBillTableList(List<UserTableBill> userBillTableList)
    {
        this.userBillTableList = userBillTableList;
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
