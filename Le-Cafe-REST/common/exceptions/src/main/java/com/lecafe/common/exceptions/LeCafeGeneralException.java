package com.lecafe.common.exceptions;

import java.io.Serializable;

public class LeCafeGeneralException extends LeCafeBaseException implements Serializable
{

    public LeCafeGeneralException(Exception e )
    {
        super( e );
    }
    /*
    public ApetoiGeneralException( Exception exp )
    {
        super( exp, ( ReadProperty.EXCEPTION_GENERAL ) );

    }

    public ApetoiGeneralException( Exception exp, String str )
    {
        super( exp, ( String.format( ReadProperty.EXCEPTION_GENERAL_EXTRA, str ) ) );
    }

    public ApetoiGeneralException( String str )
    {
        super( str );
    }
    */
}
