package com.lecafe.common.utilities;

import com.lecafe.common.entities.User;
import com.lecafe.common.exceptions.LeCafeLdapException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LDAP
{
    private static Logger _logger = LoggerFactory.getLogger( LDAP.class );

    private DirContext _ldapContext;
    private String _url = ReaderProperties.getValue( "ldap.url", "Ldap.properties" );
    private String _connType = ReaderProperties.getValue( "ldap.connType", "Ldap.properties" );
    private String _directory = ReaderProperties.getValue( "ldap.directory", "Ldap.properties" );
    private String _userDirectory = ReaderProperties.getValue( "ldap.dn", "Ldap.properties" );
    private String _user = ReaderProperties.getValue( "ldap.user", "Ldap.properties" );
    private String _password = ReaderProperties.getValue( "ldap.password", "Ldap.properties" );

    public LDAP() {}

    private void connectLDAP( String user, String password )
    {
        try
        {
            //region Instrumentation
            _logger.debug( "entrando a connectLDAP: user {}, password {}", user, password );
            //endregion

            Hashtable<String, String> environment = new Hashtable<String, String>();
            environment.put( "java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory" );
            environment.put( "java.naming.provider.url", _url );
            environment.put( "java.naming.security.authentication", _connType );
            environment.put( "java.naming.security.principal",
                             user == _user ? String.format( "uid=%s,ou=system", user ) :
                                     String.format( _userDirectory + "," + _directory, user ) );
            environment.put( "java.naming.security.credentials", password );

            _ldapContext = new InitialDirContext( environment );
        }
        catch ( Exception e )
        {
            throw new LeCafeLdapException( e );
        }

        //region Instrumentation
        _logger.debug( "saliendo de connectLDAP: user {}, password {}", user, password );
        //endregion
    }

    private void disconnectLDAP()
    {
        try
        {
            _logger.debug( "entrando a disconnectLDAP" );
            _ldapContext.close();
            _logger.debug( "saliendo de disconnectLDAP" );

        }
        catch ( Exception e )
        {
            throw new LeCafeLdapException( e );
        }
    }

    public void addEntryToLdap( User user )
    {
        try
        {
            _logger.debug( "entrando a addEntryToLdap: user {}", user );

            connectLDAP( _user, _password );
            Attribute oc = new BasicAttribute( "objectClass" );
            oc.add( "top" );
            oc.add( "person" );
            SimpleDateFormat format = new SimpleDateFormat( "yyyyMMddHHmm" );
            BasicAttributes entry = new BasicAttributes();
            entry.put( oc );
            entry.put( new BasicAttribute( "cn", user.getEmail() ) );
            entry.put( new BasicAttribute( "sn", user.getEmail() ) );
            entry.put( new BasicAttribute( "userpassword", user.getPassword() ) );
            entry.put( new BasicAttribute( "pwdLastSuccess", format.format( new Date() ) + "Z" ) );
            _ldapContext.createSubcontext( String.format( _userDirectory + "," + _directory, user.getEmail() ), entry );
            _logger.debug( "saliendo de addEntryToLdap: user {}", user );

        }
        catch ( Exception e )
        {
            //region Instrumentation ERROR
            _logger.error( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(), e.getCause() );
            //endregion

            throw new LeCafeLdapException( e );
        }
        finally
        {
            disconnectLDAP();
        }
    }

    public void deleteEntry( User user )
    {
        try
        {
            _logger.debug( "entrando a deleteEntry: user {}", user );

            connectLDAP( _user, _password );
            _ldapContext.destroySubcontext( String.format( _userDirectory + "," + _directory, user.getEmail() ) );
            _logger.debug( "saliendo de deleteEntry: user {}", user );

        }
        catch ( Exception e )
        {
            throw new LeCafeLdapException( e );
        }
        finally
        {
            disconnectLDAP();
        }
    }

    public void getEntry( User user )
    {
        try
        {
            _logger.debug( "entrando a getEntry: user {}", user );

            connectLDAP( _user, _password );
            SearchControls searcCon = new SearchControls();
            searcCon.setSearchScope( 2 );

            NamingEnumeration results =
                    _ldapContext.search( _directory, String.format( _userDirectory, user.getEmail() ), searcCon );
            if ( results != null )
            {
                while ( results.hasMore() )
                {
                    SearchResult res = ( SearchResult ) results.next();
                    Attributes atbs = res.getAttributes();
                    Attribute atb = atbs.get( "cn" );
                    String str = ( String ) atb.get();
                }
            }
            else
            {
                throw new LeCafeLdapException( "User " + user.getEmail() + " is not registered in ldap." );
            }
            _logger.debug( "saliendo de getEntry: user {}", user );

        }
        catch ( Exception e )
        {
            throw new LeCafeLdapException( e );
        }
        finally
        {
            disconnectLDAP();
        }
    }

    public void updateEntry( User user )
    {
        try
        {
            connectLDAP( _user, _password );
            Attributes atbs = new BasicAttributes();
            Attribute atb = new BasicAttribute( "mail", "java2db@mai.com" );
            atbs.put( atb );

            _ldapContext
                    .modifyAttributes( String.format( _userDirectory + "," + _directory, user.getEmail() ), 2, atbs );

        }
        catch ( Exception e )
        {
            throw new LeCafeLdapException( e );
        }
        finally
        {
            disconnectLDAP();
        }
    }


    public void changePassword( User user )
    {
        try
        {
            connectLDAP( _user, _password );
            ModificationItem[] modificationItems = new ModificationItem[ 2 ];
            modificationItems[ 0 ] =
                    new ModificationItem( 2, new BasicAttribute( "userpassword", user.getPassword() ) );

            modificationItems[ 1 ] = new ModificationItem( 2, new BasicAttribute( "description", "NUEVO" ) );


            _ldapContext.modifyAttributes( String.format( _userDirectory + "," + _directory, user.getEmail() ),
                                           modificationItems );
        }
        catch ( Exception e )
        {
            throw new LeCafeLdapException( e );
        }
        finally
        {
            disconnectLDAP();
        }
    }

    public void userAuthentication( User user )
    {

        try
        {
            //region Instrumentation
            _logger.debug( "entrando a ldap userAuthentication: user email {}, password {}", user.getEmail(),
                           user.getPassword() );
            //endregion

            connectLDAP( user.getEmail(), user.getPassword() );
        }
        catch ( Exception e )
        {
            //region Instrumentation ERROR
            _logger.error( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(), e.getCause() );
            //endregion

            throw new LeCafeLdapException( e );
        }
        finally
        {
            disconnectLDAP();
        }

        //region Instrumentation
        _logger.debug( "saliendo de Ldap userAuthentication: user email {}, password {}", user.getEmail(),
                       user.getPassword() );
        //endregion

    }
}
