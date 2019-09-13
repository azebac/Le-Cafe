package com.lecafe.common.exceptions.jpa;

import com.lecafe.common.exceptions.BaseException;

public class AddException extends BaseException
{
    public AddException( Exception e, String str )
    {
        super( e, str );
    }
}
