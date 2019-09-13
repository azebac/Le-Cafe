package com.lecafe.common.exceptions.handler;

import com.lecafe.common.exceptions.BaseException;

public class ADHandlerException extends BaseException
{
    public ADHandlerException( Exception e, String str )
    {
        super( e, str );
    }
}
