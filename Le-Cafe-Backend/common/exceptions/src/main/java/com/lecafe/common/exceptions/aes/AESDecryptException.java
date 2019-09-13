package com.lecafe.common.exceptions.aes;

import com.lecafe.common.exceptions.BaseException;

public class AESDecryptException extends BaseException
{
    public AESDecryptException( Exception e, String str )
    {
        super( e, str );
    }
}
