package com.lecafe.common.enums;

import java.util.HashMap;

public enum TransactionType
{
    UNDEFINED( 0 ),
    APPOINTMENT( 1 ),
    FEE( 2 ),
    REFUND( 3 );

    private int _value;
    private static HashMap<Integer, TransactionType> _map = new HashMap<>();

    TransactionType( int value )
    {
        _value = value;
    }

    static
    {
        for( TransactionType type : TransactionType.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static TransactionType valueOf( int typeNum )
    {
        TransactionType response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
