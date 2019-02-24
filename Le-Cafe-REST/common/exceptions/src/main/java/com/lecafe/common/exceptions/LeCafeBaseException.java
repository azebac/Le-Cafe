package com.lecafe.common.exceptions;

import java.io.Serializable;

public abstract class LeCafeBaseException extends RuntimeException implements Serializable
{

    //TODO revisar
    public LeCafeBaseException()
    {

    }


    public LeCafeBaseException(Exception e )
    {
        super( e );
    }

    public LeCafeBaseException(Exception e, String str )
    {
        super( str, e );
    }

    public LeCafeBaseException(String  str )
    {
        super( str );
    }

}
