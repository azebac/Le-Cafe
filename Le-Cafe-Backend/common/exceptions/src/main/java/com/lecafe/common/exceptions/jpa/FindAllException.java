package com.lecafe.common.exceptions.jpa;

import com.lecafe.common.exceptions.BaseException;

public class FindAllException extends BaseException
{
    public FindAllException( Exception e, String str )
    {
        super( e, str );
    }
}
