package com.lecafe.common.exceptions.aes;

import com.lecafe.common.exceptions.BaseException;

public class AESSetKeyException extends BaseException
{
    public AESSetKeyException( Exception e, String str )
    {
        super( e, str );
    }
}
