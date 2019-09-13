package com.lecafe.persistence;

import com.lecafe.common.enums.HandlerType;
import com.lecafe.common.exceptions.handler.DBHandlerException;
import com.lecafe.common.utilities.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class DBHandler
{
    private static final String JTA_DATASOURCE = "javax.persistence.jtaDataSource";
    private static final String DATASOURCE_URL = "javax.persistence.jdbc.url";
    private static final String DATASOURCE_USER = "javax.persistence.jdbc.user";
    private static final String DATASOURCE_PASSWORD = "javax.persistence.jdbc.password";

    private static EntityManagerFactory _emf;
    private static Logger _logger = LoggerFactory.getLogger( DBHandler.class );

    private EntityManager _em;
    private EntityTransaction _tx;

    static
    {
        getEntityManagerFactory();
    }

    public EntityManager getSession()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a DBHandler.getSession" );
        //endregion

        try
        {
            if ( _em == null )
                _em = _emf.createEntityManager();
        }
        catch ( Exception e )
        {
            throw new DBHandlerException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de DBHandler.getSession: EntityManager {}", _em );
        //endregion

        return _em;
    }

    public void closeSession()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a DBHandler.closeSession" );
        //endregion

        try
        {
            if ( _em != null )
            {
                _em.close();
                _em = null;
            }
        }
        catch ( Exception e )
        {
            throw new DBHandlerException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de DBHandler.closeSession" );
        //endregion
    }

    public void beginTransaction()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a DBHandler.beginTransaction" );
        //endregion

        if ( _em == null )
            getSession();

        if ( _tx == null )
            _tx = _em.getTransaction();

        if ( !_tx.isActive() )
            _tx.begin();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de DBHandler.beginTransaction: EntityTransaction {}", _tx );
        //endregion

    }

    public void finishTransaction()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a DBHandler.finishTransaction" );
        //endregion

        if ( _tx != null && _tx.isActive() )
        {
            _tx.commit();
            _tx = null;
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de DBHandler.finishTransaction" );
        //endregion
    }

    private static void getEntityManagerFactory()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a DBHandler.getEntityManagerFactory" );
        //endregion

        Map<String, String> properties = new HashMap<>();

        if( getType().equals( HandlerType.SERVER ) )
        {
            properties.put( JTA_DATASOURCE, Registry.getInstance().getProperty( Registry.DB_JNDI ) );
        }
        else
        {
            properties.put( DATASOURCE_URL, Registry.getInstance().getProperty( Registry.DB_URL ) );
            properties.put( DATASOURCE_USER, Registry.getInstance().getProperty( Registry.DB_USER ) );
            properties.put( DATASOURCE_PASSWORD, Registry.getInstance().getProperty( Registry.DB_PASSWORD ) );
        }

        try
        {
            _emf = Persistence.createEntityManagerFactory(
                    Registry.getInstance().getProperty( Registry.DB_UNIT ), properties );
        }
        catch ( Exception e )
        {
            throw new DBHandlerException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de DBHandler.getEntityManagerFactory" );
        //endregion
    }

    private static HandlerType getType()
    {
        return HandlerType.valueOf( Registry.getInstance().getProperty( Registry.DB_TYPE ) );
    }
}
