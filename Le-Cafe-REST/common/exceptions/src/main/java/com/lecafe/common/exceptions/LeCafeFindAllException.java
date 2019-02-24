package com.lecafe.common.exceptions;

import java.io.Serializable;

public class LeCafeFindAllException extends LeCafeBaseException implements Serializable
{

    public LeCafeFindAllException()
    {
        //super();
    }

    public LeCafeFindAllException(Exception e, String s )
    {
        //todo mejorar
        //super( e, ( String.format( ReadProperty.EXCEPTION_FIND_ALL, str ) ) );
    }
}
