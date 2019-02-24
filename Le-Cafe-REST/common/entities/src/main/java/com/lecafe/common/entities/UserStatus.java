package com.lecafe.common.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_status")
public class UserStatus extends BaseEntity
{
    @Column(name="name")
    private String name;

    @OneToMany( mappedBy = "status", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<User> userList;

    public UserStatus()
    {
    }

    public UserStatus(long id)
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

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }
}
