package com.lecafe.common.exceptions.activeDirectory;

import com.lecafe.common.exceptions.BaseException;

public class DirFindException extends BaseException
{
    public DirFindException( Exception e, String str )
    {
        super( e, str );
    }
}
