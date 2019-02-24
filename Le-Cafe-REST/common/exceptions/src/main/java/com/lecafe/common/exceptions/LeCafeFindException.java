package com.lecafe.common.exceptions;

import java.io.Serializable;

public class LeCafeFindException extends LeCafeBaseException implements Serializable
{

    public LeCafeFindException()
    {
        super();
    }

    public LeCafeFindException(Exception e, String str )
    {
        super( e, str );
    }

    public LeCafeFindException(String str )
    {
        super( str );
    }
}
