package com.lecafe.common.exceptions.jpa;

import com.lecafe.common.exceptions.BaseException;

public class DeleteException extends BaseException
{
    public DeleteException( Exception e, String str )
    {
        super( e, str );
    }
}
