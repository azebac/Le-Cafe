package com.lecafe.common.exceptions.aes;

import com.lecafe.common.exceptions.BaseException;

public class AESAlreadyDecryptException extends BaseException
{
    public AESAlreadyDecryptException( Exception e, String str )
    {
        super( e, str );
    }
}
