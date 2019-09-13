package com.lecafe.persistence.dao;

import com.lecafe.common.entities.User;
import com.lecafe.common.exceptions.activeDirectory.DirAddException;
import com.lecafe.common.exceptions.activeDirectory.DirDeleteException;
import com.lecafe.common.exceptions.activeDirectory.DirAuthException;
import com.lecafe.common.exceptions.activeDirectory.DirFindException;
import com.lecafe.common.exceptions.activeDirectory.DirUpdateException;
import com.lecafe.persistence.ADHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;


public class ActiveDirectoryDAO
{
    private static final String USER_COMMON_NAME = "CN";
    private static final String USER_SUR_NAME = "SN";
    private static final String USER_PASSWORD = "userPassword";

    private ADHandler _handler;

    private static Logger _logger = LoggerFactory.getLogger( ActiveDirectoryDAO.class );


    public ActiveDirectoryDAO()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ActiveDirectoryDAO.CTOR" );
        //endregion

        _handler = new ADHandler();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ActiveDirectoryDAO.CTOR: _handler {}", _handler );
        //endregion
    }


    public void insert( User user )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ActiveDirectoryDAO.insert: User {}", user );
        //endregion

        try
        {
            LdapContext context = _handler.getContext();
            String login = _handler.getADUserLogin( user.getEmail() );

            Attribute objectClass = new BasicAttribute( "objectClass" );
            objectClass.add( "top" ); //Super object class
            objectClass.add( "person" ); //Person. Contexts with person need common name (CN) and surname (SN)

            Attributes attr = new BasicAttributes( true );
            attr.put( objectClass );
            attr.put( USER_COMMON_NAME, user.getEmail() );
            attr.put( USER_SUR_NAME, user.getEmail() );
            attr.put( USER_PASSWORD, user.getPassword() );

            context.createSubcontext( login, attr );
            _handler.closeContext( context );
        }
        catch( Exception e )
        {
            throw new DirAddException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ActiveDirectoryDAO.insert" );
        //endregion
    }


    public void update( User user )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ActiveDirectoryDAO.update: User {}", user );
        //endregion

        try
        {
            LdapContext context = _handler.getContext();
            String login = _handler.getADUserLogin( user.getEmail() );

            Attributes attr = new BasicAttributes( true );
            attr.put( USER_PASSWORD, user.getNewPassword() );

            context.modifyAttributes( login, DirContext.REPLACE_ATTRIBUTE, attr );
            _handler.closeContext( context );
        }
        catch( Exception e )
        {
            throw new DirUpdateException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ActiveDirectoryDAO.update" );
        //endregion
    }


    public void delete( User user )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ActiveDirectoryDAO.delete: User {}", user );
        //endregion

        try
        {
            LdapContext context = _handler.getContext();
            String login = _handler.getADUserLogin( user.getEmail() );

            context.destroySubcontext( login );
            _handler.closeContext( context );
        }
        catch( Exception e )
        {
            throw new DirDeleteException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ActiveDirectoryDAO.delete" );
        //endregion
    }


    public User find( User user )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ActiveDirectoryDAO.find: User {}", user );
        //endregion

        try
        {
            LdapContext context = _handler.getContext();

            SearchControls search = new SearchControls();
            search.setSearchScope( 2 );

            NamingEnumeration commonNames =
                    context.search( _handler.getUserDomain(), _handler.getUserCommonName( user.getEmail() ), search );

            SearchResult result = ( SearchResult ) commonNames.next();
            user.setPassword( ( String ) result.getAttributes().get( USER_PASSWORD ).get() );
        }
        catch( Exception e )
        {
            throw new DirFindException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ActiveDirectoryDAO.find: User {}", user );
        //endregion

        return user;
    }


    public void auth( User user )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ActiveDirectoryDAO.auth: User {}", user );
        //endregion

        try
        {
            LdapContext context = _handler.getContext( user.getEmail(), user.getPassword() );
            _handler.closeContext( context );
        }
        catch( Exception e )
        {
            throw new DirAuthException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ActiveDirectoryDAO.auth" );
        //endregion
    }
}
