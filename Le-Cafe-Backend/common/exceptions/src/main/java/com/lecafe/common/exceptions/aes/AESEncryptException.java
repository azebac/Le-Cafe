package com.lecafe.common.exceptions.aes;

import com.lecafe.common.exceptions.BaseException;

public class AESEncryptException extends BaseException
{
    public AESEncryptException( Exception e, String str )
    {
        super( e, str );
    }
}
