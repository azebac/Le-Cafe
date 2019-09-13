package com.lecafe.common.exceptions.jwt;

import com.lecafe.common.exceptions.BaseException;

public class JWTVerifyException extends BaseException
{
    public JWTVerifyException( Exception e, String str )
    {
        super( e, str );
    }
}
