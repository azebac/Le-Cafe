package com.lecafe.common.enums;

import java.util.HashMap;

public enum AppointmentStatus
{
    UNDEFINED( 0 ),
    CREATED( 1 ),
    PAID( 2 ),
    STARTED( 3 ),
    NO_HISTORY( 4 ),
    NO_COMMENT_MEDIC( 5 ),
    NO_COMMENT_PATIENT( 6 ),
    UNPAID( 7 ),
    FINISHED( 8 ),
    CANCELED( 9 ),
    RESCHEDULE_PATIENT( 10 ),
    REQUEST_RESCHEDULE( 11 ),
    LOST( 12 );

    private int _value;
    private static HashMap<Integer, AppointmentStatus> _map = new HashMap<>();

    AppointmentStatus( int value )
    {
        _value = value;
    }

    static
    {
        for( AppointmentStatus type : AppointmentStatus.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static AppointmentStatus valueOf( int typeNum )
    {
        AppointmentStatus response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
