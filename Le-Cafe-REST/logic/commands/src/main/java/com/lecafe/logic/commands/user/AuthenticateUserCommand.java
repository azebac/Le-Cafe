package com.lecafe.logic.commands.user;

import com.lecafe.common.entities.User;
import com.lecafe.logic.commands.Command;
import com.lecafe.persistence.DAOFactory;
import com.lecafe.persistence.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticateUserCommand extends Command<User>
{
    private User _entity;
    private UserDAO _dao;

    private static Logger _logger = LoggerFactory.getLogger( AuthenticateUserCommand.class );

    public AuthenticateUserCommand( User entity )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a AuthenticateUserCommand.CTOR: entity {}", entity.toString() );
        //endregion

        _entity = entity;
        _dao = DAOFactory.createUserDAO();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de AuthenticateUserCommand.CTOR: _entity {}, _dao {}", _entity.toString(),
                       _dao.toString() );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a AuthenticateUserCommand.execute" );
        //endregion

        _entity = _dao.userAuthentication( _entity );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de AuthenticateUserCommand.execute: _entity {}", _entity.toString() );
        //endregion
    }

    @Override
    public User getReturnParam()
    {

        return _entity;
    }
}
