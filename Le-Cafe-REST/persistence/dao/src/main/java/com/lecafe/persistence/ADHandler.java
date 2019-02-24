package com.lecafe.persistence;

import com.lecafe.common.exceptions.LeCafeLdapException;
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

    public static final int UF_ACCOUNTDISABLE = 0x0002;
    public static final int UF_PASSWD_NOTREQD = 0x0020;
    public static final int UF_NORMAL_ACCOUNT = 0x0200;
    public static final int UF_PASSWORD_EXPIRED = 0x800000;
    public static final int UF_TEMP_DUPLICATE_ACCOUNT = 0x0100;
    public static final int UF_INTERDOMAIN_TRUST_ACCOUNT = 0x0800;
    public static final int UF_WORKSTATION_TRUST_ACCOUNT = 0x1000;
    public static final int UF_SERVER_TRUST_ACCOUNT = 0x2000;
    public static final int UF_DONT_EXPIRE_PASSWD = 0x10000;
    public static final int UF_SCRIPT = 0x0001;
    public static final int UF_HOMEDIR_REQUIRED = 0x0008;
    public static final int UF_LOCKOUT = 0x0010;
    public static final int UF_PASSWD_CANT_CHANGE = 0x0040;

    private static Logger _logger = LoggerFactory.getLogger( ADHandler.class );
    private final Registry _registry = Registry.getInstance();


    public LdapContext getADContext()
    {
        Hashtable<String, String> env = new Hashtable<String, String>();
        LdapContext context;
        StringBuilder url = new StringBuilder();
        StringBuilder admin = new StringBuilder();

        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.getADContext" );
        //endregion

        env.put( Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY );
        env.put( Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION );

        url.append( _registry.getProperty( Registry.AD_CONNECTION_TYPE ) );
        url.append( _registry.getProperty( Registry.AD_SERVER ) ).append( ":" );
        url.append( _registry.getProperty( Registry.AD_PORT )  );
        env.put( Context.PROVIDER_URL, url.toString() );

        admin.append( getADAdminLogin() );
        env.put( Context.SECURITY_PRINCIPAL, admin.toString() );

        env.put( Context.SECURITY_CREDENTIALS, _registry.getProperty( Registry.AD_ADMIN_PASS ) );
        env.put( Context.REFERRAL, _registry.getProperty( Registry.AD_REFERRAL ) );

        try
        {
            context = new InitialLdapContext( env, null );
        }
        catch ( NamingException e )
        {
            throw new LeCafeLdapException( e );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.getADContext: context {}", context.toString() );
        //endregion

        return context;
    }


    private String getADAdminLogin()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.getADAdmin" );
        //endregion

        StringBuilder admin = new StringBuilder();

        admin.append( _registry.getProperty( Registry.AD_ADMIN ) ).append( "@" );
        admin.append( _registry.getProperty( Registry.AD_DOMAIN ) );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.getADAdmin: AD admin {}", admin.toString() );
        //endregion

        return admin.toString();
    }



    public String getADUser( String email )
    {
        StringBuilder builder = new StringBuilder();

        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.getADUser: entity {}", email );
        //endregion

        builder.append( "CN=" ).append( email ).append( "," );
        builder.append( "OU=" ).append( _registry.getProperty( _registry.AD_USER_GROUP ) );

        String[] domain = _registry.getProperty( _registry.AD_DOMAIN ).split( "\\." );

        for ( String aDomain : domain )
            builder.append( ",DC=" ).append( aDomain );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.getADUser: AD username {}", builder.toString() );
        //endregion

        return builder.toString();

    }





    public byte[] encodePassword( String password )
    {
        final byte SHIFT_EIGHT = 8;
        final int BITWISE_VALUE = 0xff;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a ADHandler.encodePassword: password {}", password );
        //endregion

        String quotedPassword = "\"" + password + "\"";

        char[] unicodePwd = quotedPassword.toCharArray();
        byte[] pwdArray = new byte[ unicodePwd.length * 2 ];

        for ( int i = 0; i < unicodePwd.length; i++ )
        {
            pwdArray[ i * 2 + 1 ] = ( byte )( unicodePwd[ i ] >>> SHIFT_EIGHT );
            pwdArray[ ( i * 2 ) ] = ( byte )( unicodePwd[ i ] & BITWISE_VALUE );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ADHandler.encodePassword: password {}", pwdArray.toString() );
        //endregion

        return pwdArray;
    }



}
