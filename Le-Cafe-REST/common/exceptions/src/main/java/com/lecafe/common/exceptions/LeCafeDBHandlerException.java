package com.lecafe.common.exceptions;




public class LeCafeDBHandlerException extends LeCafeBaseException
{
    private final String _msg = "ERROR con el DBHandler";


    public String getMsg()
    {
        return _msg;
    }


    public LeCafeDBHandlerException(Exception e )
    {

        super( e );
    }


}
