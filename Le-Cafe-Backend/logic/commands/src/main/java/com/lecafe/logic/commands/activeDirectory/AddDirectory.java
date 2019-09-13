package com.lecafe.logic.commands.activeDirectory;

import com.lecafe.common.entities.User;
import com.lecafe.logic.commands.Command;
import com.lecafe.persistence.DAOFactory;
import com.lecafe.persistence.dao.ActiveDirectoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddDirectory extends Command<Boolean>
{
    private ActiveDirectoryDAO _ad;
    private User _user;

    private static Logger _logger = LoggerFactory.getLogger( AddDirectory.class );

    public AddDirectory( User user )
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a AddDirectory.CTOR: User {}", user );
        //endregion

        _ad = DAOFactory.createActiveDirectoryDAO();

        _user = user;

        //Instrumentation DEBUG
        _logger.debug( "saliendo de AddDirectory.CTOR" );
        //endregion
    }

    @Override
    public void execute()
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a AddDirectory.execute" );
        //endregion

        _ad.insert( _user );

        //Instrumentation DEBUG
        _logger.debug( "saliendo de AddDirectory.execute" );
        //endregion
    }

    @Override
    public Boolean getReturnParam()
    {
        throw new UnsupportedOperationException();
    }
}
