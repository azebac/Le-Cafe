package com.lecafe.common.exceptions.mail;

import com.lecafe.common.exceptions.BaseException;

public class CreateMailException extends BaseException
{
    public CreateMailException( Exception e, String str )
    {
        super( e, str );
    }
}
