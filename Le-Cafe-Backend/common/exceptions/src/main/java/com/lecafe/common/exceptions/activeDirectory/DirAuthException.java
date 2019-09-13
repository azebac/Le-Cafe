package com.lecafe.common.exceptions.activeDirectory;

import com.lecafe.common.exceptions.BaseException;

public class DirAuthException extends BaseException
{
    public DirAuthException( Exception e, String str )
    {
        super( e, str );
    }
}
