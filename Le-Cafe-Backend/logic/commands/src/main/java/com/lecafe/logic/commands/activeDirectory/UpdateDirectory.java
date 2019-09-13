package com.lecafe.logic.commands.activeDirectory;

import com.lecafe.common.entities.User;
import com.lecafe.logic.commands.Command;
import com.lecafe.persistence.DAOFactory;
import com.lecafe.persistence.dao.ActiveDirectoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateDirectory extends Command<Boolean>
{
    private ActiveDirectoryDAO _ad;
    private User _user;

    private static Logger _logger = LoggerFactory.getLogger( UpdateDirectory.class );

    public UpdateDirectory( User user )
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a UpdateDirectory.CTOR: User {}", user );
        //endregion

        _ad = DAOFactory.createActiveDirectoryDAO();

        _user = user;

        //Instrumentation DEBUG
        _logger.debug( "saliendo de UpdateDirectory.CTOR" );
        //endregion
    }

    @Override
    public void execute()
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a UpdateDirectory.execute" );
        //endregion

        _ad.update( _user );

        //Instrumentation DEBUG
        _logger.debug( "saliendo de UpdateDirectory.execute" );
        //endregion
    }

    @Override
    public Boolean getReturnParam()
    {
        throw new UnsupportedOperationException();
    }
}
