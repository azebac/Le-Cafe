package com.lecafe.common.exceptions.mail;

import com.lecafe.common.exceptions.BaseException;

public class SendMailException extends BaseException
{
    public SendMailException( Exception e, String str )
    {
        super( e, str );
    }
}
