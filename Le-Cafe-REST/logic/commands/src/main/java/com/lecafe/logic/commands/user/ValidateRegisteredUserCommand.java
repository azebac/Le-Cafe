package com.lecafe.logic.commands.user;

import com.lecafe.common.entities.User;
import com.lecafe.logic.commands.Command;
import com.lecafe.persistence.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateRegisteredUserCommand extends Command<User>
{
    private User _user;
    private static Logger _logger = LoggerFactory.getLogger( ValidateRegisteredUserCommand.class );
    private UserDAO _dao;
    private User _result;

    public ValidateRegisteredUserCommand( User user )
    {
        _user = user;
        _dao = new UserDAO();
    }

    @Override
    public void execute()
    {
        try
        {
            //region Instrumentation DEBUG
            _logger.debug( "entrando a ValidateRegisteredUserCommand.execute" );
            //endregion
            _result = _dao.validateRegisteredUser( _user );
            //region Instrumentation DEBUG
            _logger.debug( "saliendo de ValidateRegisteredUserCommand.execute: size {}, lista {}", _user.toString() );
            //endregion
        }
        catch ( Exception ex )
        {

        }
    }

    @Override
    public User getReturnParam()
    {
        return _result;
    }
}
