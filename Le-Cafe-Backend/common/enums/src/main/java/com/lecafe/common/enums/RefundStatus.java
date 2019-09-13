package com.lecafe.common.enums;

import java.util.HashMap;

public enum RefundStatus
{
    UNDEFINED( 0 ),
    REFUNDED( 1 ),
    NOT_REFUNDED( 2 );

    private int _value;
    private static HashMap<Integer, RefundStatus> _map = new HashMap<>();

    RefundStatus( int value )
    {
        _value = value;
    }

    static
    {
        for( RefundStatus type : RefundStatus.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static RefundStatus valueOf( int typeNum )
    {
        RefundStatus response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
