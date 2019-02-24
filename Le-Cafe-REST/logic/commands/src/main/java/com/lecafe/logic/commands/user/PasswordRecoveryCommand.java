package com.lecafe.logic.commands.user;

import com.lecafe.common.entities.User;
import com.lecafe.common.utilities.CommonFunctions;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.CommandFactory;
import com.lecafe.persistence.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

public class PasswordRecoveryCommand extends Command<User>
{

    private User _user;
    private static Logger _logger = LoggerFactory.getLogger( PasswordRecoveryCommand.class );
    private UserDAO _dao;
    private Command[] _commands;
    private final int _long = 3;

    public PasswordRecoveryCommand( User user )
    {
       /* _commands = new Command[ _long ];
        _commands[ 0 ] = CommandFactory.createValidateRegisteredUserCommand( user );
        _user.setPassword( CommonFunctions.generatePassword() );
        _commands[ 1 ] = CommandFactory.createChangeUserPasswordCommand( user );
        Hashtable<String,String> emailContent = createEmailNotification( user );*/
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a PasswordRecoveryCommand.execute" );
        //endregion
        _commands[ 0 ].execute();
        _commands[ 1 ].execute();
        _commands[ 2 ].execute();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de PasswordRecoveryCommand.execute: size {}, lista {}", _user.toString() );
        //endregion
    }

    @Override
    public User getReturnParam()
    {
        return null;
    }

    private Hashtable<String,String> createEmailNotification(User user)
    {
        Hashtable<String,String> emailContent = new Hashtable<String,String>();
        emailContent.put( "To", user.getEmail());
        emailContent.put( "Name", user.getNames() + user.getLastnames() );
        emailContent.put( "Password", user.getPassword());
        //TODO cambiar idioma por el contenido en la entidad
        emailContent.put( "Language", "ES");
        return emailContent;
    }
}

