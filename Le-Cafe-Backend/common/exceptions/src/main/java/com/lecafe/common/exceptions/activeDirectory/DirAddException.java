package com.lecafe.common.exceptions.activeDirectory;

import com.lecafe.common.exceptions.BaseException;

public class DirAddException extends BaseException
{
    public DirAddException( Exception e, String str )
    {
        super( e, str );
    }
}
