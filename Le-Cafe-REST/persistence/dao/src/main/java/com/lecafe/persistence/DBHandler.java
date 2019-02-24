package com.lecafe.persistence;

import com.lecafe.common.exceptions.LeCafeDBHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class DBHandler
{

    private static EntityManagerFactory _emf;
    private EntityManager _em;
    private EntityTransaction _et;
    private boolean _isTransaction;


    private static Logger _logger = LoggerFactory.getLogger( DBHandler.class );

    //TODO falta agregar instrumentacion a esta clase

    /**
     *
     */
    public DBHandler()
    {
        if ( _emf == null )
        {
            try
            {   //todo cambiar el cable del nombre
                _emf = Persistence.createEntityManagerFactory( "lecafe-test" );

            }
            catch ( PersistenceException e )
            {
                throw new LeCafeDBHandlerException( e );
            }
            catch ( RuntimeException e )
            {
                throw new LeCafeDBHandlerException( e );
            }
            catch ( Exception e )
            {
                throw new LeCafeDBHandlerException( e );
            }

        }
    }

    /**
     * @return
     */
    public EntityManager getSession()
    {
        try
        {
            if ( _em == null )
            {
                _em = _emf.createEntityManager();
            }
        }
        catch ( PersistenceException e )
        {
            throw new LeCafeDBHandlerException( e );
        }
        catch ( RuntimeException e )
        {
            throw new LeCafeDBHandlerException( e );
        }
        catch ( Exception e )
        {
            throw new LeCafeDBHandlerException( e );
        }
        return _em;
    }

    /**
     *
     */
    public void closeSession()
    {
        try
        {
            if ( _em != null && _emf != null )
            {
                _em.close();
                _em = null;

                _emf.close();
                _emf = null;
            }
        }
        catch ( PersistenceException e )
        {
            throw new LeCafeDBHandlerException( e );
        }
        catch ( RuntimeException e )
        {
            throw new LeCafeDBHandlerException( e );
        }
        catch ( Exception e )
        {
            throw new LeCafeDBHandlerException( e );
        }
    }

    /**
     * @return
     */
    public Object beginTransaction()
    {
        if ( _em == null )
        {
            getSession();
        }
        if ( _et == null )
        {
            _et = _em.getTransaction();
        }
        if ( !_et.isActive() )
        {
            _et.begin();
        }

        return _et;
    }

    /**
     *
     */
    public void finishTransaction()
    {
        if ( _et != null && _et.isActive() )
        {
            _et.commit();
            _et = null;
        }
    }

    /**
     *
     */
    public void revertTransaction()
    {
        if ( _et != null && _et.isActive() )
        {
            _et.rollback();
            _et = null;
        }
    }

    /**
     * @return
     */
    public boolean isTransaction()
    {
        return _isTransaction;
    }

    /**
     * @param transaction
     */
    public void setIsTransaction( boolean transaction )
    {
        _isTransaction = transaction;
    }

}

