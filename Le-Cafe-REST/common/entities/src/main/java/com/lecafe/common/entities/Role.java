package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Role")
public class Role extends BaseEntity
{
    @Column(name="name")
    private String name;

    @OneToMany( mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<UserRole> userRoleList;

    public Role()
    {
    }

    public Role(long id)
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

    public List<UserRole> getUserRoleList()
    {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList)
    {
        this.userRoleList = userRoleList;
    }
}
