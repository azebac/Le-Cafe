package com.lecafe.common.enums;

import java.util.HashMap;

public enum WeekDay
{
    UNDEFINED( 0 ),
    MONDAY( 1 ),
    TUESDAY( 2 ),
    WEDNESDAY( 3 ),
    THURSDAY( 4 ),
    FRIDAY( 5 ),
    SATURDAY( 6 ),
    SUNDAY( 7 );

    private int _value;
    private static HashMap<Integer, WeekDay> _map = new HashMap<>();

    WeekDay( int value )
    {
        _value = value;
    }

    static
    {
        for ( WeekDay type : WeekDay.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static WeekDay valueOf( int typeNum )
    {
        WeekDay response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
