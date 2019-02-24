package com.lecafe.common.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "Gender" )
public class Gender extends BaseEntity
{

    /**
     * Description:            UserStatus's Description Attribute
     */
    @Column( name = "description", nullable = false, length = 45 )
    private String description;

    @OneToMany( mappedBy = "gender", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<User> userList;


    /**
     * Name:                   Gender
     * Description:            Method that initialize Gender's class
     */
    public Gender( long id )
    {
        super( id );
    }


    /**
     * Name:                UserStatus
     * Description:         Method to initialize a Gender (Empty constructor)
     */
    public Gender()
    {
    }

    /**
     * Name:                   getDescription
     * Description:            Method that return Gender's Description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Name:                   setDescription
     * Description:            Method that set a Gender's Description
     */
    public void setDescription( String description )
    {
        this.description = description;
    }


    public List<User> getUserList()
    {
        return userList;
    }


    public void setUserList( List<User> userList )
    {
        this.userList = userList;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "Gender{" );
        sb.append( super.toString() );
        sb.append( '}' );
        return sb.toString();
    }

    @Override
    public boolean equals( Object o )
    {
        return super.equals( o );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( super.hashCode() );
    }

}
