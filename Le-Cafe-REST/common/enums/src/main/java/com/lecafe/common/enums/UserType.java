package com.lecafe.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserType
{
    SINVALOR( 0 ),
    ADMINISTRATOR( 1 ),
    PACIENTE( 2 ),
    MEDICO( 3 );

    private int _value;
    private static Map _map = new HashMap<>();

    UserType( int value )
    {
        _value = value;
    }

    static
    {
        for( UserType type : UserType.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static UserType valueOf( int typeNum )
    {
        UserType temp = ( UserType ) _map.get( typeNum );

        if( temp == null )
            throw new IllegalArgumentException( "No enum constant was found for value : " + typeNum );

        return temp;
    }

    public int getValue()
    {
        return _value;
    }

}
