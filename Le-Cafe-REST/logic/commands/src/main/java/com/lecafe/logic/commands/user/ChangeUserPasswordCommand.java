package com.lecafe.logic.commands.user;

import com.lecafe.common.entities.User;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.CommandFactory;
import com.lecafe.persistence.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

public class ChangeUserPasswordCommand extends Command<User>
{
    private User _user;
    private static Logger _logger = LoggerFactory.getLogger( ChangeUserPasswordCommand.class );
    private Command[] _commands;
    private UserDAO _dao;

    public ChangeUserPasswordCommand( User user )
    {
        _user = user;
        _dao = new UserDAO();
        _commands = new Command[ 2 ];
        _commands[ 0 ] = CommandFactory.createAuthenticateUserCommand( user );
        Hashtable<String,String> emailContent = createEmailNotification( user );
       // _commands[ 1 ] = CommandFactory.createSendMailCommand( emailContent, "changePassword" );
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a PasswordRecoveryCommand.execute" );
        //endregion

        _commands[ 0 ].execute();
        _dao.changeUserPassword( _user );
        _commands[ 1 ].execute();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de PasswordRecoveryCommand.execute: size {}, lista {}", _user.toString() );
        //endregion
    }

    @Override
    public User getReturnParam()
    {
        return null;
    }

    private Hashtable<String, String> createEmailNotification( User user )
    {
        Hashtable<String, String> emailContent = new Hashtable<String, String>();
        emailContent.put( "To", user.getEmail() );
        emailContent.put( "Name", user.getNames() + user.getLastnames() );
        //TODO cambiar idioma por el contenido en la entidad
        emailContent.put( "Language", "ES" );
        return emailContent;
    }

}
