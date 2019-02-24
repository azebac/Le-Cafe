package com.lecafe.common.exceptions;

import java.io.Serializable;

public class LeCafeUpdateException extends LeCafeBaseException implements Serializable
{

    public LeCafeUpdateException()
    {
        super();
    }

    public LeCafeUpdateException(Exception e, String str )
    {
        super( e, str );
    }

    public LeCafeUpdateException(String str )
    {
        super( str );
    }
}
