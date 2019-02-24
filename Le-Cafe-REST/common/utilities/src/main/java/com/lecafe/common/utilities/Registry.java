package com.lecafe.common.utilities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Registry
{
    public static final String PROPERTIES_FILE = "aidpass.properties";

    public static final String AD_CONNECTION_TYPE = "ad.connectionType";
    public static final String AD_SERVER = "ad.server";
    public static final String AD_PORT = "ad.port";
    public static final String AD_ADMIN_PASS = "ad.adminPassword";
    public static final String AD_REFERRAL = "ad.referral";
    public static final String AD_ADMIN = "ad.admin";
    public static final String AD_DOMAIN = "ad.domain";
    public static final String AD_USER_GROUP = "ad.userGroup";

    private static Logger _logger = LoggerFactory.getLogger( Registry.class );

    private static Registry _instance;
    private Properties _props;
    private boolean _isPropertiesLoaded;


    public Properties getProps()
    {
        return _props;
    }

    public void setProps( Properties props )
    {
        if ( props != null && !_isPropertiesLoaded )
        {
            _props = props;
            _isPropertiesLoaded = true;
        }
        else if ( props == null )
            throw new IllegalArgumentException( "properties null" );
    }

    public String getProperty( String key )
    {
        return _props.getProperty( key );
    }

    public boolean isPropertiesLoaded()
    {
        return _isPropertiesLoaded;
    }

    private Registry()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a Registry.CTOR" );
        //endregion

        _props = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream( PROPERTIES_FILE );

        if ( inputStream != null )
        {
            try
            {
                _props.load( inputStream );
                _isPropertiesLoaded = true;
            }
            catch ( IOException e )
            {
                _logger.error( e.getMessage(), e );
                _isPropertiesLoaded = false;
                //throw new LeCafeConfigException( e );
                // TODO agregar codigo de failover ?

            }
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de Registry.CTOR: properties {}", _props.toString() );
        //endregion
    }

    public static Registry getInstance()
    {
        if( _instance == null )
            _instance = new Registry();

        return _instance;
    }



}
