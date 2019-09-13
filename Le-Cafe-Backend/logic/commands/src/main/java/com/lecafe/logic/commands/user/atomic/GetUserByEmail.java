package com.lecafe.logic.commands.user.atomic;

import com.lecafe.common.entities.User;
import com.lecafe.common.utilities.AES;
import com.lecafe.logic.commands.Command;
import com.lecafe.persistence.DAOFactory;
import com.lecafe.persistence.DBHandler;
import com.lecafe.persistence.dao.UserDAO;
import com.lecafe.logic.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUserByEmail extends Command<User>
{
    private User _entity;
    private UserDAO _dao;

    private static Logger _logger = LoggerFactory.getLogger( GetUserByEmail.class );

    public GetUserByEmail( User user )
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a GetUserByEmail.CTOR: user {}", user );
        //endregion

        createSession( false );

        _entity = user;
        _dao = DAOFactory.createUserDAO( getHandler() );

        //Instrumentation DEBUG
        _logger.debug( "saliendo de GetUserByEmail.CTOR" );
        //endregion
    }

    public GetUserByEmail( User user, DBHandler handler )
    {
        super( handler );

        //Instrumentation DEBUG
        _logger.debug( "entrando a GetUserByEmail.CTOR: user {}, handler {}", user, handler );
        //endregion

        _entity = user;
        _dao = DAOFactory.createUserDAO( getHandler() );

        //Instrumentation DEBUG
        _logger.debug( "saliendo de GetUserByEmail.CTOR" );
        //endregion
    }

    @Override
    public void execute()
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a GetUserByEmail.execute" );
        //endregion

        _entity.setEmail( AES.encrypt( _entity.getEmail() ) );

        _entity = _dao.findByEmail( _entity );

        //Instrumentation DEBUG
        _logger.debug( "saliendo de GetUserByEmail.execute: entity {}", _entity );
    }

    @Override
    public User getReturnParam()
    {
        return _entity;
    }
}
