package com.lecafe.common.exceptions.handler;

import com.lecafe.common.exceptions.BaseException;

public class DBHandlerException extends BaseException
{
    public DBHandlerException( Exception e, String str )
    {
        super( e, str );
    }
}
