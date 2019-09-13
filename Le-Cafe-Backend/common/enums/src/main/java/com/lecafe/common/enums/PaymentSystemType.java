package com.lecafe.common.enums;

import java.util.HashMap;

public enum PaymentSystemType
{
    UNDEFINED( 0 ),
    PAYPAL( 1 ),
    MERCADOPAGO( 2 ),
    STRIPE(3),
    INSTAPAGO(4);

    private int _value;
    private static HashMap<Integer, PaymentSystemType> _map = new HashMap<>();

    PaymentSystemType( int value )
    {
        _value = value;
    }

    static
    {
        for( PaymentSystemType type : PaymentSystemType.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static PaymentSystemType valueOf( int typeNum )
    {
        PaymentSystemType response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
