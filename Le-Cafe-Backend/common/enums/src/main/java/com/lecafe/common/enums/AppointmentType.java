package com.lecafe.common.enums;

import java.util.HashMap;

public enum AppointmentType
{
    UNDEFINED( 0 ),
    APPOINTMENT( 1 ),
    EMERGENCY( 2 );

    private int _value;
    private static HashMap<Integer, AppointmentType> _map = new HashMap<>();

    AppointmentType( int value )
    {
        _value = value;
    }

    static
    {
        for( AppointmentType type : AppointmentType.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static AppointmentType valueOf( int typeNum )
    {
        AppointmentType response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
