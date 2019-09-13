package com.lecafe.logic.commands.user.composite;

import com.lecafe.common.email.Email;
import com.lecafe.common.entities.User;
import com.lecafe.common.utilities.CommonFunctions;
import com.lecafe.common.utilities.Registry;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.CommandFactory;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RecoverPassword extends Command<User>
{
    private User _entity;

    private static Logger _logger = LoggerFactory.getLogger( RecoverPassword.class );

    public RecoverPassword( User user )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando RecoverPassword.CTOR: user {}", user );
        //endregion

        createSession( false );

        _entity = user;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a RecoverPassword.CTOR" );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a RecoverPassword.execute" );
        //endregion

        Command<User> command = CommandFactory.createGetUserByEmail( _entity, getHandler() );
        command.execute();

        _entity = command.getReturnParam();
        _entity.setNewPassword( CommonFunctions.generatePassword() );

        _entity.decrypt();


        _entity.encrypt();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de RecoverPassword.execute" );
        //endregion
    }


    @Override
    public User getReturnParam()
    {
        return _entity;
    }
}
