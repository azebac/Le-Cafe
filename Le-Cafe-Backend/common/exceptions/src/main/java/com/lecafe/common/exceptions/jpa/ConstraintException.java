package com.lecafe.common.exceptions.jpa;

import com.lecafe.common.exceptions.BaseException;

public class ConstraintException extends BaseException
{
    public ConstraintException( Exception e, String str )
    {
        super( e, str );
    }
}
