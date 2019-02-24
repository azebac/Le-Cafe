package com.lecafe.common.utilities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Properties;

public class BaseProperties
{

    private static Logger _logger = LoggerFactory.getLogger( BaseProperties.class );

    public static final String GENERAL_PROPERTIES = "General.properties";
    public static final String EXCEPCIONES_PROPERTIES = "Excepciones.properties";
    public static final String LDAP_PROPERTIES = "Ldap.properties";

    public static final String ERROR = "Error reading the properties ";

    public static HashMap<String, Properties> _propertiesList = new HashMap();

    public static Properties getProperties( String infile )
    {
        _logger.debug( "entrando a getProperties: file {}", infile );


        Properties properties = null;

        try
        {
            properties = getPropertiesRegistration( infile );

            if ( properties == null )
            {
                properties = new Properties();
                InputStream file = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream( File.separatorChar + infile );

                if ( file == null )
                    file = Thread.currentThread().getContextClassLoader().getResourceAsStream( infile );

                Reader reader = new InputStreamReader( file, Charset.forName( "UTF-8" ) );
                properties.load( reader );
                file.close();
                assignProperties( properties, infile );
            }
        }
        catch ( Exception e )
        {
            _logger.error( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(),
                                          e.getCause() );
            throw new InvalidParameterException( MessageFormat.format( ERROR, infile ) );
        }
        _logger.debug( "entrando a getProperties: file {}",
                       infile );
        return properties;
    }

    private static Properties getPropertiesRegistration( String name )
    {

        return _propertiesList.get( name );

    }


    private static void assignProperties( Properties properties, String name )
    {
        _propertiesList.put( name, properties );
    }

}
