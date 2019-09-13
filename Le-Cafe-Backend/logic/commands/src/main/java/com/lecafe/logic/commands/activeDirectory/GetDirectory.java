package com.lecafe.logic.commands.activeDirectory;

import com.lecafe.common.entities.User;
import com.lecafe.logic.commands.Command;
import com.lecafe.persistence.DAOFactory;
import com.lecafe.persistence.dao.ActiveDirectoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetDirectory extends Command<User>
{
    private User _user;
    private ActiveDirectoryDAO _ad;

    private static Logger _logger = LoggerFactory.getLogger( GetDirectory.class );

    public GetDirectory( User user )
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a GetDirectory.CTOR: user {}", user );
        //endregion

        _ad = DAOFactory.createActiveDirectoryDAO();

        _user = user;

        //Instrumentation DEBUG
        _logger.debug( "saliendo de GetDirectory.CTOR" );
        //endregion
    }

    @Override
    public void execute()
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a GetDirectory.execute" );
        //endregion

        _user = _ad.find( _user );

        //Instrumentation DEBUG
        _logger.debug( "saliendo de GetDirectory.execute: entity {}", _user );
        //endregion
    }

    @Override
    public User getReturnParam()
    {
        return _user;
    }
}
