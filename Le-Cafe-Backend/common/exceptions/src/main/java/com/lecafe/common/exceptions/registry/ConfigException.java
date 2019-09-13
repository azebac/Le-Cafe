package com.lecafe.common.exceptions.registry;

import com.lecafe.common.exceptions.BaseException;

public class ConfigException extends BaseException
{
    public ConfigException( Exception e, String str )
    {
        super( e, str );
    }
}
