package com.lecafe.common.exceptions.activeDirectory;

import com.lecafe.common.exceptions.BaseException;

public class DirDeleteException extends BaseException
{
    public DirDeleteException( Exception e, String str )
    {
        super( e, str );
    }
}
