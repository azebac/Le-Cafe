package com.lecafe.common.enums;

import java.util.HashMap;

public enum DocumentSupportType
{
    UNDEFINED( 0 ),
    LICENSE( 1 ),
    SIGNATURE( 2 ),
    DEGREE( 3 ),
    OTHER( 4 );

    private int _value;
    private static HashMap<Integer, DocumentSupportType> _map = new HashMap<>();

    DocumentSupportType( int value )
    {
        _value = value;
    }

    static
    {
        for ( DocumentSupportType type : DocumentSupportType.values() )
        {
            _map.put( type._value, type );
        }
    }

    public static DocumentSupportType valueOf( int typeNum )
    {
        DocumentSupportType response = _map.get( typeNum );

        if( response == null )
            throw new IllegalArgumentException( "No enum constant was found for value: " + typeNum );

        return response;
    }

    public int getValue()
    {
        return _value;
    }
}
