package com.lecafe.common.utilities;

import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class CommonFunctions
{
    private static Logger _logger = LoggerFactory.getLogger( CommonFunctions.class );

    public static String generatePassword()
    {
        return generate( Registry.getInstance().getProperty( Registry.PASSWORD_CHARACTERS ),
                         Integer.parseInt( Registry.getInstance().getProperty( Registry.PASSWORD_LENGTH ) ) );
    }

    public static String generatedPin()
    {
        return generate( Registry.getInstance().getProperty( Registry.PIN_CHARACTERS ),
                         Integer.parseInt( Registry.getInstance().getProperty( Registry.PIN_LENGTH ) ) );
    }

    private static String generate( String characters, int length )
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a CommonFunctions.generate" );
        //endregion

        StringBuilder response = new StringBuilder();
        Random random = new Random();

        do
        {
            response.append( characters.charAt( random.nextInt( characters.length() ) ) );
        }
        while ( response.length() < length );

        //Instrumentation DEBUG
        _logger.debug( "saliendo de CommonFunctions.generate: response {}", response );
        //endregion

        return response.toString();
    }

    public static String toCamelCase( String text )
    {
        String value = text;

        if( text != null )
            value = WordUtils.capitalize( text );

        return value;
    }


}
