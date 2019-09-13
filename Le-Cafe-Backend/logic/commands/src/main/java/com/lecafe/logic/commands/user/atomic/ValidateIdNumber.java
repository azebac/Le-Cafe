package com.lecafe.logic.commands.user.atomic;

import com.lecafe.common.entities.User;
import com.lecafe.common.exceptions.jpa.NotFoundException;
import com.lecafe.common.utilities.AES;
import com.lecafe.logic.commands.Command;
import com.lecafe.persistence.DAOFactory;
import com.lecafe.persistence.dao.UserDAO;
import com.lecafe.logic.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateIdNumber extends Command<Boolean>
{
    private User _entity;
    private UserDAO _dao;
    private boolean _result;

    private static Logger _logger = LoggerFactory.getLogger( ValidateIdNumber.class );

    public ValidateIdNumber( User user )
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a ValidateIdNumber.CTOR: user {}", user );
        //endregion

        createSession( false );

        _entity = user;
        _result = true;
        _dao = DAOFactory.createUserDAO( getHandler() );

        //Instrumentation DEBUG
        _logger.debug( "saliendo de ValidateIdNumber.CTOR" );
        //endregion
    }

    @Override
    public void execute()
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a ValidateIdNumber.execute" );
        //endregion

        try
        {
            _entity.setIdNumber( AES.encrypt( _entity.getIdNumber() ) );

            _dao.findByIdNumber( _entity );
        }
        catch ( NotFoundException e )
        {
            _result = false;
        }

        //Instrumentation DEBUG
        _logger.debug( "saliendo de ValidateIdNumber.execute: _result {}", _result );
    }

    @Override
    public Boolean getReturnParam()
    {
        return _result;
    }
}
