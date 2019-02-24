package com.lecafe.common.entities;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseEntity
{
    /*
    Description:            BaseEntity's Id attribute that every Entity will inherit
    */
    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;


    /**
     * Description:            Method that returns BaseEntity's Id
     */
    public long getId()
    {
        return _id;
    }

    /**
     * Description:            Method that set BaseEntity's Id
     */
    public void setId( long id )
    {
        if ( id <= 0 )
            throw new IllegalArgumentException( String.format( "id: {}", _id ) ); //fixme resource?
        _id = id;
    }

    /*
    BaseEntity's Constructor
     */
    public BaseEntity()
    {

    }

    /*
    BaseEntity's Constructor
     */
    public BaseEntity( long id )
    {
        setId( id );
    }


    @Override
    public boolean equals( Object o )
    {
        final BaseEntity that;

        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        that = ( BaseEntity ) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }


    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "BaseEntity{" );
        sb.append( "_id=" ).append( _id );
        sb.append( '}' );
        return sb.toString();
    }


}
