package com.lecafe.logic.dtos;

public class MasterDTO
{
    public long _id;
    public String _value;
    public byte _status;


    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "MasterDTO{" );
        sb.append( "id=" ).append( _id );
        sb.append( ", _value='" ).append( _value ).append( '\'' );
        sb.append( ", _status=" ).append( _status );
        sb.append( '}' );
        return sb.toString();
    }
}
