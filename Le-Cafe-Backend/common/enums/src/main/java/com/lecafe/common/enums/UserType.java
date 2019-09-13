package com.lecafe.common.enums;

import java.util.HashMap;

public enum UserType
{
    UNDEFINED( 0 ),
    ADMINISTRATOR( 1 ),
    PATIENT( 2 ),
    MEDIC( 3 );

    private int _value;
    private static HashMap<Integer, UserType> _map = new HashMap<>();

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
        UserType response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
