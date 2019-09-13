package com.lecafe.services.implementations;

import com.lecafe.common.EntityFactory;
import com.lecafe.common.entities.User;
import com.lecafe.common.enums.UserType;
import com.lecafe.common.exceptions.jwt.JWTVerifyException;
import com.lecafe.common.exceptions.registry.ConfigException;
import com.lecafe.common.utilities.JWT;
import com.lecafe.common.utilities.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@ApplicationPath( "/api" )
public class BaseApplicationService extends Application
{
    private static Logger _logger = LoggerFactory.getLogger( BaseApplicationService.class );

    private User _credential;

    @Override
    public Set<Class<?>> getClasses()
    {
        final HashSet<Class<?>> response = new HashSet<>();

        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseApplicationService.getClasses" );
        //endregion

        try
        {
            Registry.getInstance();

            response.add( UserService.class );
        }
        catch( ConfigException e )
        {
            _logger.error( e.getMessage(), e );
            throw new Error( e.getMessage(), e );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseApplicationService.getClasses" );
        //endregion

        return response;
    }

    User getCredential()
    {
        return _credential;
    }

    private void setCredential( String credential )
    {
        try
        {
            List<String> values = JWT.verifyToken( credential );

            _credential = EntityFactory.createUser();
            //_credential.setUserType( UserType.valueOf( Integer.valueOf( values.get( 0 ) ) ) );
            _credential.setId( Long.valueOf( values.get( 1 ) ) );
        }
        catch ( JWTVerifyException e )
        {
            _logger.error( e.getMessage(), e );
            throwException( Response.Status.UNAUTHORIZED, e );
        }
    }



    void verifyParams( Object object )
    {
        if ( object == null )
            throwException( Response.Status.BAD_REQUEST );
    }


    void throwException( Response.Status status, Exception e )
    {
        throw new WebApplicationException( Response.status( status ).entity( e ).build() );
    }

    void registerTransaction()
    {
       /* try
        {
            if ( Objects.nonNull( _transaction ) )
            {
                if ( Objects.isNull( _transaction.getUser() ) )
                {
                    _transaction.setUser( getCredential() );
                }

                Command<Boolean> command = CommandFactory.createAddRecord( _transaction );

                command.execute();
                command.closeSession();

                _transaction = null;
            }
        }
        catch ( Exception e )
        {
            _logger.error( e.getMessage(), e );
        }*/
    }

    private void throwException( Response.Status status )
    {
        throw new WebApplicationException( Response.status( status ).build() );
    }
}
