package com.lecafe.common.exceptions.activeDirectory;

import com.lecafe.common.exceptions.BaseException;

public class DirUpdateException extends BaseException
{
    public DirUpdateException( Exception e, String str )
    {
        super( e, str );
    }
}
