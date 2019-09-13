package com.lecafe.common.enums;

import java.util.HashMap;

public enum RecordStatus
{
    UNDEFINED( 0 ),
    EXECUTED( 1 ),
    NOT_EXECUTED( 2 );

    private int _value;
    private static HashMap<Integer, RecordStatus> _map = new HashMap<>();

    RecordStatus(int value )
    {
        _value = value;
    }

    static
    {
        for( RecordStatus type : RecordStatus.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static RecordStatus valueOf(int typeNum )
    {
        RecordStatus response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
