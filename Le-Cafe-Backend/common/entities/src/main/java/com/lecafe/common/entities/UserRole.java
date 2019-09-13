package com.lecafe.common.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole extends BaseEntity
{
    @ManyToOne
    @JoinColumn( name = "fk_user" )
    private User user;

    @ManyToOne
    @JoinColumn( name = "fk_role" )
    private Role role;

    public UserRole()
    {
    }

    public UserRole(long id)
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

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }
}
