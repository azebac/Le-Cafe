package com.lecafe.common.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;


@MappedSuperclass
public abstract class MasterEntity extends BaseEntity
{
    @Column( name = "description", nullable = false, length = 45 )
    private String _value;

    @Column( name = "status", nullable = false )
    private byte _status; // FIXME cambiar por enum?


    public MasterEntity()
    {
        super();
    }

    public MasterEntity( long id )
    {
        super( id );
    }




    public byte getStatus()
    {
        return _status;
    }

    public void setStatus( byte status )
    {
        _status = status;
    }

    public String getValue()
    {
        return _value;
    }

    public void setValue( String value )
    {
        _value = value;
    }


    @Override
    public boolean equals( Object o )
    {
        final MasterEntity that;

        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        if ( !super.equals( o ) )
        {
            return false;
        }

        that = ( MasterEntity ) o;

        return getStatus() == that.getStatus() && Objects.equals( getValue(), that.getValue() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( super.hashCode(), getStatus(), getValue() );
    }


    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "MasterEntity{" );
        sb.append( super.toString() ).append( ", " );
        sb.append( "_status=" ).append( _status );
        sb.append( ", _value='" ).append( _value ).append( '\'' );
        sb.append( '}' );
        return sb.toString();
    }
}
