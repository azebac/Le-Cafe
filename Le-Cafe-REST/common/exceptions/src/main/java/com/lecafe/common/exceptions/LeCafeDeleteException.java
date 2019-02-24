package com.lecafe.common.exceptions;

import java.io.Serializable;

public class LeCafeDeleteException extends LeCafeBaseException implements Serializable
{

    public LeCafeDeleteException()
    {
        super();
    }

    public LeCafeDeleteException(Exception e, String str )
    {
        super( e, str );
    }

    public LeCafeDeleteException(String str )
    {
        super( str );
    }
}

