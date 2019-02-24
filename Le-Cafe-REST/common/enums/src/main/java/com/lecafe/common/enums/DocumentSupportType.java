package com.lecafe.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum DocumentSupportType
{

    LICENSE( 0 ),
    SIGNATURE( 1 ),
    DEGREE( 2 ),
    OTHER( 3 );

    private int _value;
    private static Map _map = new HashMap<>();


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

    public static DocumentSupportType valueOf( short docType )
    {
        DocumentSupportType temp = ( DocumentSupportType ) _map.get( docType );

        if( temp == null )
            throw new IllegalArgumentException( "No enum constant was found for value : " + docType );

        return temp;
    }
}
