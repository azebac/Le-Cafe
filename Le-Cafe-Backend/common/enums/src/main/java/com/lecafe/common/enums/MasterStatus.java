package com.lecafe.common.enums;

import java.util.HashMap;

public enum MasterStatus
{
    UNDEFINED( 0 ),
    ACTIVE( 1 ),
    INACTIVE( 2 );

    private int _value;
    private static HashMap<Integer, MasterStatus> _map = new HashMap<>();

    MasterStatus( int value )
    {
        _value = value;
    }

    static
    {
        for( MasterStatus type : MasterStatus.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static MasterStatus valueOf( int typeNum )
    {
        MasterStatus response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
