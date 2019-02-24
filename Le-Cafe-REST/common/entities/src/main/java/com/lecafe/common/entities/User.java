package com.lecafe.common.entities;


import com.lecafe.common.enums.UserStatus;
import com.lecafe.common.enums.UserType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "User" )
@Inheritance( strategy = InheritanceType.JOINED )
public class User extends BaseEntity
{
    //region Attributes

    @Transient
    private String password;

    @Transient
    private String newPassword;

    @Column( name = "name", nullable = false, length = 45 )
    private String names;

    @Column( name = "lastname", nullable = false, length = 45 )
    private String lastnames;

    @Column( name = "birthdate" )
    private LocalDate birthdate;

    @Column( name = "email", nullable = false, unique = true, length = 150 )
    private String email;

    @Column(name="points")
    private int points;

    @ManyToOne
    @JoinColumn( name = "fk_gender", nullable = false )
    private Gender gender;

    @ManyToOne
    @JoinColumn( name = "fk_status", nullable = false )
    private UserStatus status;

    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<UserRole> userRoleList;

    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<UserTableBill> userBillTableList;

    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Reservation> userReservationList;

    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<UserReward> userRewardList;




    //endregion

    //region Constructors

    public User()
    {

    }

    public User( long id )
    {
        super( id );
    }

    //endregion

    //region Properties

    public String getNames()
    {
        return names;
    }

    public void setNames( String names )
    {
        this.names = names;
    }

    public String getLastnames()
    {
        return lastnames;
    }

    public void setLastnames( String lastnames )
    {
        this.lastnames = lastnames;
    }

    public LocalDate getBirthdate()
    {
        return birthdate;
    }

    public UserStatus getStatus()
    {
        return status;
    }

    public void setStatus(UserStatus status)
    {
        this.status = status;
    }

    public List<UserRole> getUserRoleList()
    {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList)
    {
        this.userRoleList = userRoleList;
    }

    public List<Reservation> getUserReservationList()
    {
        return userReservationList;
    }

    public void setUserReservationList(List<Reservation> userReservationList)
    {
        this.userReservationList = userReservationList;
    }

    public List<UserTableBill> getUserBillTableList()
    {
        return userBillTableList;
    }

    public void setUserBillTableList(List<UserTableBill> userBillTableList)
    {
        this.userBillTableList = userBillTableList;
    }

    public void setBirthdate(LocalDate birthdate )
    {
        this.birthdate = birthdate;
    }


    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword( String newPassword )
    {
        this.newPassword = newPassword;
    }


    public Gender getGender()
    {
        return gender;
    }

    public void setGender( Gender gender )
    {
        this.gender = gender;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }



    //endregion

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "User{" );
        sb.append( super.toString() );
        sb.append( "names='" ).append(names).append( '\'' );
        sb.append( ", lastnames='" ).append(lastnames).append( '\'' );
        sb.append( ", points='" ).append(points).append( '\'' );
        sb.append( ", _birthDate=" ).append(birthdate);
        sb.append( ", email='" ).append(email).append( '\'' );
        sb.append( ", password='" ).append(password).append( '\'' );
        sb.append( ", newPassword='" ).append(newPassword).append( '\'' );
        sb.append( ", gender=" ).append(gender);
        sb.append( '}' );
        return sb.toString();
    }



}
