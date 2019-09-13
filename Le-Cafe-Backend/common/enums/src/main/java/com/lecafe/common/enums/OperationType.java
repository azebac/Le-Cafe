package com.lecafe.common.enums;

import java.util.HashMap;

public enum OperationType
{
    UNDEFINED( 0 ),
    DEBIT( 1 ),
    ACCREDIT( 2 );

    private int _value;
    private static HashMap<Integer, OperationType> _map = new HashMap<>();

    OperationType( int value )
    {
        _value = value;
    }

    static
    {
        for( OperationType type : OperationType.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static OperationType valueOf( int typeNum )
    {
        OperationType response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
