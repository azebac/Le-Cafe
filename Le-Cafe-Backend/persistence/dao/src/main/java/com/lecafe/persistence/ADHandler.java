package com.lecafe.persistence;

import com.lecafe.common.exceptions.handler.ADHandlerException;
import com.lecafe.common.utilities.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class ADHandler
{
    private static final String CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    private static final String SECURITY_AUTHENTICATION = "simple";

    private static Logger _logger = LoggerFactory.getLogger( ADHandler.class );
    private final Registry _registry = Registry.getInstance();

    public LdapContext getContext( String user, String password )
    {
        Hashtable<String, String> env = new Hashtable<>();
        LdapContext context;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.getContext: user {}, password {}", user, password );
        //endregion

        setContext( env );
        env.put( Context.SECURITY_PRINCIPAL, getADUserLogin( user ) );
        env.put( Context.SECURITY_CREDENTIALS, password );
        context = connect( env );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.getContext: context {}", context );
        //endregion

        return context;
    }


    public LdapContext getContext()
    {
        Hashtable<String, String> env = new Hashtable<>();
        LdapContext context;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.getContext" );
        //endregion

        setContext( env );
        env.put( Context.SECURITY_PRINCIPAL, getADAdminLogin() );
        env.put( Context.SECURITY_CREDENTIALS, _registry.getProperty( Registry.AD_ADMIN_PASS ) );
        context = connect( env );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.getContext: context {}", context );
        //endregion

        return context;
    }


    public void closeContext( LdapContext context )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.closeContext: context {}", context );
        //endregion

       try
       {
           context.close();
       }
       catch( NamingException e )
       {
           _logger.error( "Error closing context {}", context );
       }

       //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.closeContext" );
       //endregion
    }


    public String getADUserLogin( String email )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.getADUserLogin: email {}", email );
        //endregion

        StringBuilder user = new StringBuilder();

        user.append( getUserCommonName( email ) ).append( "," );
        user.append( getUserDomain() );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.getADUserLogin: user {}", user );
        //endregion

        return user.toString();
    }


    public String getUserCommonName( String email )
    {
        StringBuilder result = new StringBuilder();

        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.getUserCommonName: email {}", email );
        //endregion

        result.append( "CN=" ).append( email );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.getUserCommonName: result {}", result );
        //endregion

        return result.toString();
    }


    public String getUserDomain()
    {
        StringBuilder result = new StringBuilder();

        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.getUserDomain" );
        //endregion

        result.append( "OU=" ).append( _registry.getProperty( Registry.AD_USER_GROUP ) );

        String[] domain = _registry.getProperty( Registry.AD_DOMAIN ).split( "\\." );

        for ( String aDomain : domain )
            result.append( ",DC=" ).append( aDomain );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.getUserDomain: result {}", result );
        //endregion

        return result.toString();
    }


    private String getADAdminLogin()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.getADAdmin" );
        //endregion

        StringBuilder admin = new StringBuilder();

        admin.append( "UID=" ).append( _registry.getProperty( Registry.AD_ADMIN ) ).append( ',' );
        admin.append( "OU=" ).append( _registry.getProperty( Registry.AD_ADMIN_GROUP ) );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.getADAdmin: admin {}", admin );
        //endregion

        return admin.toString();
    }


    private void setContext( Hashtable<String, String> env )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.setContext: environment {}", env );
        //endregion

        StringBuilder url = new StringBuilder();

        env.put( Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY );
        env.put( Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION );

        url.append( _registry.getProperty( Registry.AD_CONNECTION_TYPE ) );
        url.append( _registry.getProperty( Registry.AD_SERVER ) ).append( ":" );
        url.append( _registry.getProperty( Registry.AD_PORT )  );
        env.put( Context.PROVIDER_URL, url.toString() );

        //env.put( Context.REFERRAL, _registry.getProperty( Registry.AD_REFERRAL ) );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.setContext: environment {}", env );
        //endregion
    }


    private LdapContext connect( Hashtable<String, String> env )
    {
        LdapContext context;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.connect: environment {}", env );
        //endregion

        try
        {
            context = new InitialLdapContext( env, null );
        }
        catch ( NamingException e )
        {
            throw new ADHandlerException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.connect: context {}", context );
        //endregion

        return context;
    }
}
