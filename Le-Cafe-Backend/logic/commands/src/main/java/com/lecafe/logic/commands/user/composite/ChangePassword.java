package com.lecafe.logic.commands.user.composite;

import com.lecafe.common.email.Email;
import com.lecafe.common.entities.User;
import com.lecafe.common.utilities.Registry;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.CommandFactory;
import com.lecafe.persistence.DAOFactory;
import com.lecafe.persistence.dao.UserDAO;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ChangePassword extends Command<Boolean>
{
    private User _entity;
    private UserDAO _dao;

    private static Logger _logger = LoggerFactory.getLogger( ChangePassword.class );

    public ChangePassword( User user )
    {
        //region Instrumentation DEBUG
         _logger.debug( "entrando a ChangePassword.CTOR: user {}", user );
         //endregion

        createSession( false );

        _entity = user;
        _dao = DAOFactory.createUserDAO( getHandler() );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ChangePassword.CTOR" );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a ChangePassword.execute" );
        //endregion

        User user = _dao.find( _entity.getId() );
        user.setPassword( _entity.getPassword() );
        user.setNewPassword( _entity.getNewPassword() );

        user.decrypt();

        CommandFactory.createAuthenticateDirectory( user ).execute();

        user.encrypt();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de ChangePassword.execute" );
        //endregion
    }

    @Override
    public Boolean getReturnParam()
    {
        throw new UnsupportedOperationException();
    }
}
