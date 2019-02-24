package com.lecafe.persistence.dao;


import com.lecafe.common.entities.User;
import com.lecafe.common.exceptions.LeCafeLdapException;
import com.lecafe.persistence.ADHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.LdapContext;


public class ActiveDirectoryDAO
{
    private ADHandler _handler;

    private static Logger _logger = LoggerFactory.getLogger( ActiveDirectoryDAO.class );


    public ActiveDirectoryDAO()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ActiveDirectoryDAO.CTOR" );
        //endregion

        _handler = new ADHandler();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ActiveDirectoryDAO.CTOR: _handler {}", _handler.toString() );
        //endregion
    }


    public void insert( User entity )
    {
        LdapContext context = null;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a ActiveDirectoryDAO.insert: entidad {}", entity.toString() );
        //endregion

        try
        {
            context = _handler.getADContext();
            if ( context != null )
            {
                String usr = _handler.getADUser( entity.getEmail() );

                Attributes attr = new BasicAttributes( true );
                attr.put( "objectClass", "user" );
                attr.put( "samAccountName", entity.getEmail() );
                attr.put( "cn", entity.getEmail() );
                attr.put( "userPrincipalName", entity.getEmail() );
                attr.put( "givenName", entity.getNames() );
                attr.put( "unicodePwd", _handler.encodePassword( entity.getPassword() ) );
                attr.put( "userAccountControl", Integer.toString(
                    _handler.UF_NORMAL_ACCOUNT + _handler.UF_PASSWD_NOTREQD + _handler.UF_DONT_EXPIRE_PASSWD ) );
                context.createSubcontext( usr, attr );
            }
        }
        catch( Exception e )
        {
            ///region Instrumentaion ERROR
            _logger.error( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(), e.getCause() );
            //endregion
            throw new LeCafeLdapException( e, entity.toString() );
        }
        finally
        {
            try
            {
                if ( context != null )
                    context.close();
            }
            catch( NamingException e ){} // no importa que se desaparezca la excepcion porque se esta cerrando
        }
        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ActiveDirectoryDAO.insert" );
        //endregion
    }


    public void authenticate( User entity )
    {

        /*try
        {
            Hashtable env = new Hashtable();
            env.put(Context.PROVIDER_URL, "ldap://mydomain.com:389/");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_PRINCIPAL, "mydomain\\" + username);
            env.put(Context.SECURITY_CREDENTIALS, password);

            DirContext context = new InitialDirContext(env);
        }
        catch( Exception e )
        {
            ///region Instrumentaion ERROR
            _logger.error( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(), e.getCause() );
            //endregion
            throw new LeCafeLdapException( e, entity.toString() );
        }*/
    }

}
