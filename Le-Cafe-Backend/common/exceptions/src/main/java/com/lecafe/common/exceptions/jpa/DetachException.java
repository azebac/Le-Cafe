package com.lecafe.common.exceptions.jpa;

import com.lecafe.common.exceptions.BaseException;

public class DetachException extends BaseException
{
    public DetachException( Exception e, String str )
    {
        super( e, str );
    }
}
