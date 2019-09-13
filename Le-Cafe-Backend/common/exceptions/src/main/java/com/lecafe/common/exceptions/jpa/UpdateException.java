package com.lecafe.common.exceptions.jpa;

import com.lecafe.common.exceptions.BaseException;

public class UpdateException extends BaseException
{
    public UpdateException( Exception e, String str )
    {
        super( e, str );
    }
}
