package com.lecafe.common.exceptions.jwt;

import com.lecafe.common.exceptions.BaseException;

public class JWTCreateException extends BaseException
{
    public JWTCreateException( Exception e, String str )
    {
        super( e, str );
    }
}
