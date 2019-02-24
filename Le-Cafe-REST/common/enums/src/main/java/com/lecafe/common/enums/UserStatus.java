package com.lecafe.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus
{
    SINVALOR( 0 ),
    ACTIVO( 1 ),
    ELIMINADO( 2 ),
    BLOQUEADO( 3 ),
    MOROSO( 4 );

    private int _value;

    private static Map _map = new HashMap<>();

    UserStatus( int value )
    {
        _value = value;
    }

    static
    {
        for( UserStatus type : UserStatus.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static UserStatus valueOf( int typeNum )
    {
        UserStatus temp = ( UserStatus ) _map.get( typeNum );

        if( temp == null )
            throw new IllegalArgumentException( "No enum constant was found for value : " + typeNum );

        return temp;
    }

    public int getValue()
    {
        return _value;
    }

}
