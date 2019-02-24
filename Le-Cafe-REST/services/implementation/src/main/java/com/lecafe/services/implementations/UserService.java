package com.lecafe.services.implementations;


import com.lecafe.common.entities.User;
import com.lecafe.common.exceptions.LeCafeAESEncryptException;
import com.lecafe.common.exceptions.LeCafeGeneralException;
import com.lecafe.common.exceptions.LeCafeLdapException;
import com.lecafe.logic.MapperFactory;
import com.lecafe.logic.commands.CommandFactory;
import com.lecafe.logic.commands.user.ChangeUserPasswordCommand;
import com.lecafe.logic.commands.user.PasswordRecoveryCommand;
import com.lecafe.logic.commands.user.AuthenticateUserCommand;
import com.lecafe.logic.dtos.UserDTO;
import com.lecafe.logic.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/user" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UserService extends BaseApplicationService
{
    private static Logger _logger = LoggerFactory.getLogger( UserService.class );

    @POST
    @Path( "/authenticate" )
    public UserDTO authenticateUser( UserDTO userData )
    {
        UserDTO result;
        User tempEntity;
        AuthenticateUserCommand command;
        UserMapper userMapper;
        if ( userData != null )
        {
            throw new WebApplicationException( Response.status( Response.Status.BAD_REQUEST ).build() );
        }
        //region Instrumentation
        _logger.debug( "entrando a authenticateUser: userData email {}, password {}", userData.email,
                       userData.password);
        //endregion
        try
        {
            userMapper = new UserMapper();
            tempEntity = userMapper.mapDTOToEntity( userData );
            command = CommandFactory.createAuthenticateUserCommand( tempEntity );
            command.execute();
            //region Instrumentation
            _logger.debug( "autenticar usuario ejecutado: tempEntity {}", tempEntity.toString() );
            //endregion
            tempEntity = command.getReturnParam();
            result = userMapper.mapEntityToDTO( tempEntity );
        }
        catch ( LeCafeAESEncryptException e )
        {
            //region Instrumentation ERROR
            _logger.error( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(), e.getCause() );
            //endregion

            throw new WebApplicationException(
                    Response.status( Response.Status.PRECONDITION_FAILED ).entity( e ).build() );
        }
        catch ( LeCafeLdapException e )
        {
            //region Instrumentation ERROR
            _logger.error( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(), e.getCause() );
            //endregion

            throw new WebApplicationException( Response.status( Response.Status.UNAUTHORIZED ).
                    entity( e ).build() );
        }
        catch ( Exception e )
        {
            //region Instrumentation ERROR
            _logger.error( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(), e.getCause() );
            //endregion
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( new LeCafeGeneralException( e ) ).build() );
        }
        //region Instrumentation
        _logger.debug( "saliendo de authenticateUser: result {}", result.toString() );
        //endregion
        return result;
    }

    @POST
    @Path( "/recorverpassword" )
    public UserDTO recoverPassword( UserDTO userData )
    {
        User tempEntity = null;
        UserDTO result = null;
        PasswordRecoveryCommand command;
        UserMapper userMapper;

        if ( userData != null )
        {
            throw new WebApplicationException( Response.status( Response.Status.BAD_REQUEST ).build() );
        }

        //region Instrumentation
        _logger.debug( "entrando a recoverPassword: email {}, password {}", userData.email,
                       userData.password);
        //endregion

        try
        {
            userMapper = MapperFactory.createUserMapper();
            tempEntity = userMapper.mapDTOToEntity( userData );
            /*command = CommandFactory.createPasswordRecoveryCommand( tempEntity );
            command.execute();*/

            //region Instrumentation
            _logger.debug( "registrar medico ejecutado: email {}, password {}", userData.email,
                           userData.password);
            //endregion

            //tempEntity = command.getReturnParam();
            result = userMapper.mapEntityToDTO( tempEntity );
        }
        catch ( Exception e )
        {
            throw new WebApplicationException( Response.status( Response.Status.BAD_REQUEST ).build() );
        }

        //region Instrumentation
        _logger.debug( "saliendo de recoverPassword: email {}, password {}", userData.email,
                       userData.password);
        //endregion

        return result;
    }


    @POST
    @Path( "/changepassword" )
    public void changePassword( UserDTO userData )
    {
        User tempEntity = null;
        UserDTO result = null;
        ChangeUserPasswordCommand command;
        UserMapper userMapper;

        if ( userData != null )
        {
            throw new WebApplicationException( Response.status( Response.Status.BAD_REQUEST ).build() );
        }

        //region Instrumentation
        _logger.debug( "entrando a recoverPassword: email {}, password {}", userData.email,
                       userData.password);
        //endregion

        try
        {
            userMapper = MapperFactory.createUserMapper();
            tempEntity = userMapper.mapDTOToEntity( userData );
            //command = CommandFactory.createChangeUserPasswordCommand( tempEntity );
            //command.execute();

            //region Instrumentation
            _logger.debug( "registrar medico ejecutado: email {}, password {}", userData.email,
                           userData.password);
            //endregion

        }
        catch ( Exception e )
        {
            throw new WebApplicationException( e.getMessage(), Response.status( Response.Status.BAD_REQUEST ).build() );
        }

        //region Instrumentation
        _logger.debug( "saliendo de recoverPassword: email {}, password {}", userData.email,
                       userData.password);
        //endregion
    }

}
