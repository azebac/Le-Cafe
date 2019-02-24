package com.lecafe.common.exceptions;

public class LeCafeLdapException extends LeCafeBaseException
{
    public LeCafeLdapException(Exception e)
    {
        super(e);
    }

    public LeCafeLdapException(Exception e, String str)
    {
        super(e, str);
    }

    public LeCafeLdapException(String str)
    {
        super(str);
    }
}
