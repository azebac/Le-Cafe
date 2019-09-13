package com.lecafe.common.exceptions.jwt;

import com.lecafe.common.exceptions.BaseException;

public class JWTSetKeyException extends BaseException
{
    public JWTSetKeyException( Exception e, String str )
    {
        super( e, str );
    }
}
