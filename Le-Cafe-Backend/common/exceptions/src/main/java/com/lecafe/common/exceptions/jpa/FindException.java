package com.lecafe.common.exceptions.jpa;

import com.lecafe.common.exceptions.BaseException;

public class FindException extends BaseException
{
    public FindException( Exception e, String str )
    {
        super( e, str );
    }
}
