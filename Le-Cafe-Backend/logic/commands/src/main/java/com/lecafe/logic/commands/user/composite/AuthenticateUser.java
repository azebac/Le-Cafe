package com.lecafe.logic.commands.user.composite;

import com.lecafe.common.entities.User;
import com.lecafe.common.utilities.JWT;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.CommandFactory;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticateUser extends Command<User>
{
    private User _entity;
    private User _result;

    private static Logger _logger = LoggerFactory.getLogger( AuthenticateUser.class );

    public AuthenticateUser( User entity )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a AuthenticateUser.CTOR: entity {}", entity );
        //endregion

        createSession( true );

        _entity = entity;

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de AuthenticateUser.CTOR" );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a AuthenticateUser.execute" );
        //endregion

        CommandFactory.createAuthenticateDirectory( _entity ).execute();

        Command<User> command = CommandFactory.createGetUserByEmail( _entity, getHandler() );
        command.execute();

        _result = command.getReturnParam();


        //region Instrumentation DEBUG
        _logger.debug( "saliendo de AuthenticateUser.execute: entity {}", _entity );
        //endregion
    }

    @Override
    public User getReturnParam()
    {
        return _result;
    }
}
