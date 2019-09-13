package com.lecafe.persistence.dao;

import com.lecafe.common.exceptions.jpa.AddException;
import com.lecafe.common.exceptions.jpa.DetachException;
import com.lecafe.common.exceptions.jpa.FindAllException;
import com.lecafe.common.exceptions.jpa.FindException;
import com.lecafe.common.exceptions.jpa.DeleteException;
import com.lecafe.common.exceptions.jpa.ConstraintException;
import com.lecafe.common.exceptions.jpa.UpdateException;
import com.lecafe.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class BaseDAO<T>
{
    private Class<T> _class;
    private DBHandler _handler;

    private static Logger _logger = LoggerFactory.getLogger( BaseDAO.class );

    public BaseDAO( DBHandler handler )
    {
        _handler = handler;
        _class = (Class<T>) ( ( ParameterizedType ) getClass().getGenericSuperclass() ).getActualTypeArguments()[ 0 ];
    }

    /**
     * Name: insert
     * Description: method for inserting a record on the DB
     *
     * @param entity entity
     */
    public T insert( T entity )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.insert: entity {}", entity );
        //endregion

        EntityManager em = _handler.getSession();

        try
        {
            em.persist( entity );
            em.flush();
        }
        catch ( PersistenceException | IllegalStateException e )
        {
            throw new ConstraintException( e, e.getMessage() );
        }
        catch ( Exception e )
        {
            throw new AddException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.insert: entity {}", entity );
        //endregion

        return entity;
    }

    /**
     * Name: update
     * Description: method for updating a record on the DB
     *
     * @param entity entity
     */
    public T update( T entity )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.update: entity {}", entity );
        //endregion

        EntityManager em = _handler.getSession();

        try
        {
            em.merge( entity );
            em.flush();
        }
        catch ( PersistenceException | IllegalStateException e )
        {
            throw new ConstraintException( e, e.getMessage() );
        }
        catch ( Exception e )
        {
            throw new UpdateException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.update: entity {}", entity );
        //endregion

        return entity;
    }

    /**
     * Name: delete
     * Description: method for deleting a record from the DB
     *
     * @param entity entity
     */
    public T delete( T entity )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.delete: entity {}", entity );
        //endregion

        EntityManager em = _handler.getSession();

        try
        {
            em.remove( entity );
            em.flush();
        }
        catch ( Exception e )
        {
            throw new DeleteException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.delete: entity {}", entity );
        //endregion

        return entity;
    }

    /**
     * Name: findAll
     * Description: method for collecting all registers from an entity
     *
     * @return entity list
     */
    public List<T> findAll()
    {
        final CriteriaQuery<T> criteriaQuery;
        final Root<T> root;

        List<T> list;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.findAll: class {}", _class );
        //endregion

        EntityManager em = _handler.getSession();

        try
        {
            criteriaQuery = em.getCriteriaBuilder().createQuery( _class );

            root = criteriaQuery.from( _class );
            criteriaQuery.select( root );

            list = em.createQuery( criteriaQuery ).getResultList();
        }
        catch ( Exception e )
        {
            throw new FindAllException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.findAll: list {}", list );
        //endregion

        return list;
    }

    /**
     * Name: auth
     * Description: method for collecting an entity
     *
     * @param id identifier
     */
    public T find( long id )
    {
        T result;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.find: id {} class {}", id, _class );
        //endregion

        try
        {
            result = _handler.getSession().find( _class, id );
        }
        catch ( Exception e )
        {
            throw new FindException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.find: entity {}", result );
        //endregion

        return result;
    }

    /**
     * Name: detach
     * Description: method for detach an entity
     *
     * @param entity entity
     */
    public void detach( T entity )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.detach: entity {}", entity );
        //endregion

        EntityManager em = _handler.getSession();

        try
        {
            em.detach( entity );
        }
        catch ( Exception e )
        {
            throw new DetachException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.detach" );
        //endregion
    }

    /**
     * Name: getDBHandler
     * Description: Get the handler
     *
     * @return handler
     */
    public DBHandler getDBHandler()
    {
        return _handler;
    }
}
