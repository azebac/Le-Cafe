package com.lecafe.services.implementations;

import com.lecafe.common.entities.User;
import com.lecafe.common.enums.RecordStatus;
import com.lecafe.common.enums.RecordType;
import com.lecafe.common.enums.UserType;
import com.lecafe.common.exceptions.activeDirectory.DirAuthException;
import com.lecafe.common.exceptions.activeDirectory.DirUpdateException;
import com.lecafe.common.exceptions.jpa.NotFoundException;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.CommandFactory;
import com.lecafe.logic.dtos.UserDTO;
import com.lecafe.logic.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/user" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UserService extends BaseApplicationService
{
    private static Logger _logger = LoggerFactory.getLogger( UserService.class );

    @POST
    @Path( "/validate/email" )
    public boolean validateEmail( UserDTO user )
    {
        boolean result = false;

        User entity;
        Command<Boolean> command;

        verifyParams( user );

        //region Instrumentation
        _logger.debug( "entrando a validateEmail: user {}", user );
        //endregion

        try
        {
            /*entity = UserMapper.mapDTOToEntity( user );
            command = CommandFactory.createValidateEmail( entity );
            command.execute();
            command.closeSession();*/

            result = true;

            //region Instrumentation
            _logger.debug( "validateEmail ejecutado" );
            //endregion
        }
        catch ( Exception e )
        {
            _logger.error( e.getMessage(), e );
            throwException( Response.Status.INTERNAL_SERVER_ERROR, e );
        }

        //region Instrumentation
        _logger.debug( "saliendo de validateEmail: result {}", result );
        //endregion

        return result;
    }

    @GET
    @Path("/helloWorld")
    public String helloWorld(){
        return "hola mundo";
    }

    @POST
    @Path( "/authenticate" )
    public UserDTO authenticateUser( UserDTO user )
    {
        User entity;
        Command<User> command;
        UserDTO result = null;

        verifyParams( user );

        //region Instrumentation
        _logger.debug( "entrando a authenticateUser: user {}", user );
        //endregion

        try
        {
            entity = UserMapper.mapDTOToEntity( user );
            command = CommandFactory.createAuthenticateUser( entity );
            command.execute();
            command.closeSession();

            result = UserMapper.mapEntityToDTO( command.getReturnParam() );

        }
        catch ( DirAuthException | NotFoundException e )
        {
            _logger.error( e.getMessage(), e );
            throwException( Response.Status.BAD_REQUEST, e );
        }
        catch ( Exception e )
        {
            _logger.error( e.getMessage(), e );
            throwException( Response.Status.INTERNAL_SERVER_ERROR, e );
        }

        //region Instrumentation
        _logger.debug( "saliendo de authenticateUser: user {}", result );
        //endregion

        return result;
    }

    @POST
    @Path( "/recoverPassword" )
    public void recoverPassword( UserDTO user )
    {
        User entity;
        Command<User> command;

        verifyParams( user );

        //region Instrumentation
        _logger.debug( "entrando a recoverPassword: user {}", user );
        //endregion

        try
        {
            entity = UserMapper.mapDTOToEntity( user );
            command = CommandFactory.createRecoverPassword( entity );
            command.execute();
            command.closeSession();

            registerTransaction();
        }
        catch ( NotFoundException | DirUpdateException e )
        {
            _logger.error( e.getMessage(), e );
            throwException( Response.Status.BAD_REQUEST, e );
        }
        catch ( Exception e )
        {
            _logger.error( e.getMessage(), e );
            throwException( Response.Status.INTERNAL_SERVER_ERROR, e );
        }

        //region Instrumentation
        _logger.debug( "saliendo de recoverPassword" );
        //endregion
    }

    @PUT
    @Path( "/changePassword" )
    public void changePassword( @HeaderParam( HttpHeaders.AUTHORIZATION ) String credential, UserDTO user )
    {
        User entity;
        Command<Boolean> command;

        verifyParams( user );
        //region Instrumentation
        _logger.debug( "entrando a changePassword: user {}", user );
        //endregion


        try
        {
            entity = UserMapper.mapDTOToEntity( user );
            command = CommandFactory.createChangePassword( entity );
            command.execute();
            command.closeSession();

        }
        catch ( DirAuthException e )
        {
            _logger.error( e.getMessage(), e );
            throwException( Response.Status.BAD_REQUEST, e );
        }
        catch ( Exception e )
        {
            _logger.error( e.getMessage(), e );
            throwException( Response.Status.INTERNAL_SERVER_ERROR, e );
        }
        finally
        {
            registerTransaction();
        }

        //region Instrumentation
        _logger.debug( "saliendo de changePassword" );
        //endregion
    }
}
