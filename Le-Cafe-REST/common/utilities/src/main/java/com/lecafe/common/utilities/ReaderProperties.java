package com.lecafe.common.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;

public class ReaderProperties extends BaseProperties
{

    private static Logger _logger = LoggerFactory.getLogger( ReaderProperties.class );


    public static String getValue( String property, String file )
    {

        _logger.debug( "saliendo de getValue: property {} , file {}", property, file );
        String resultado = "";

        final Properties prop = getProperties( file );
        resultado = prop.getProperty( property );

        _logger.debug( "saliendo de getValue: property {} , file {}", property, file );

        return resultado;

    }

}
