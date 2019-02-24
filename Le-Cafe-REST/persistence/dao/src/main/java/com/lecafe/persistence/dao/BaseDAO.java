package com.lecafe.persistence.dao;

import com.lecafe.common.entities.BaseEntity;
import com.lecafe.common.exceptions.*;
import com.lecafe.common.exceptions.LeCafeFindAllException;
import com.lecafe.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class BaseDAO<T>
{
    private Class<T> _entityClass;


    private DBHandler _handler;
    private EntityManager _em;

    private static Logger _logger = LoggerFactory.getLogger( BaseDAO.class );


    public BaseDAO()
    {
        _handler = new DBHandler();
        ParameterizedType genericSuperclass = ( ParameterizedType ) getClass().getGenericSuperclass();
        _entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[ 0 ];

    }

    public BaseDAO( DBHandler handler )
    {
        _handler = handler;
        ParameterizedType genericSuperclass = ( ParameterizedType ) getClass().getGenericSuperclass();
        _entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[ 0 ];

    }

    /**
     * Name: insert
     * Description: method for inserting a record on the DB
     *
     * @param entity entity
     *
     * @author
     * @since
     */
    public T insert( T entity )
    {

        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.insert: entidad {}", entity.toString() );
        //endregion

        try
        {
            _em = _handler.getSession();
            _handler.beginTransaction();
            _em.persist( entity );
            _em.flush();
            _handler.finishTransaction();
        }
        catch ( Exception e )
        {
            _logger.error( e.getMessage(), e );
            throw new LeCafeAddException( e, _entityClass.toString() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.insert: entidad {}", entity.toString() );
        //endregion

        return entity;
    }

    /**
     * Name: update
     * Description: method for updating a record on the DB
     *
     * @param entity entity
     *
     * @author
     * @since
     */
    public T update( T entity )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.update: entidad {}", entity.toString() );
        //endregion

        _em = _handler.getSession();

        try
        {
            _handler.beginTransaction();
            _em.merge( entity );
            _em.flush();
            _handler.finishTransaction();

        }
        catch ( Exception e )
    {
            _logger.error( e.getMessage(), e );
            throw new LeCafeUpdateException( e, entity.toString() );
        }
        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.update: entidad {}", entity.toString() );
        //endregion
        return entity;
    }


    /**
     * Name: delete
     * Description: method for deleting a record from the DB
     *
     * @param entity entity
     *
     * @author
     * @since
     */
    public T delete( T entity )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.delete: entidad {}", entity.toString() );
        //endregion

        _em = _handler.getSession();
        try
        {
            _handler.beginTransaction();
            _em.remove( entity );
            _em.flush();
            _handler.finishTransaction();

        }
        catch ( Exception e )
        {
            _logger.error( e.getMessage(), e );
            throw new LeCafeDeleteException( e, entity.toString() );
    }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.delete: entidad {}", entity.toString() );
        //endregion
        return entity;
}

    /**
     * Name: findAll
     * Description: method for collecting all registers from an entity
     *
     * @author
     * @since
     */
    public List<T> findAll()
    {
        final CriteriaBuilder criteriaBuilder;
        final CriteriaQuery<T> criteriaQuery;
        final Root<T> root;
        List<T> list = null;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.findAll" );
        //endregion

        try
        {
            _em = _handler.getSession();
            criteriaBuilder = _em.getCriteriaBuilder();
            criteriaQuery = criteriaBuilder.createQuery( _entityClass );
            root = criteriaQuery.from( _entityClass );

            criteriaQuery.select( root );

            list = _em.createQuery( criteriaQuery ).getResultList();

        }
        catch( LeCafeDBHandlerException e )
        {
            //region Instrumentation ERROR
            _logger.error( "{}, mensaje: {}, causa: {}", e.getMsg(), e.getMessage(), e.getCause(), e );
            //endregion
            throw new LeCafeFindAllException( e, _entityClass.toString() ); //todo mejorar la excepcion
        }
        catch ( Exception e )
        {
            ///region Instrumentaion ERROR
            _logger.error( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(),
                                          e.getCause() );
            //endregion
            throw new LeCafeFindAllException( e, _entityClass.toString() ); //todo mejorar la excepcion
        }
        finally
        {
            //region Instrumentation DEBUG
            _logger.debug( "cerrando la sesion en findAll" );
            //endregion

            _handler.closeSession();
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.findAll: lista {}", list.toString() );
        //endregion

        return list;
    }


    /**
     * Name: findAllXId
     * Description:  method for collecting all registers from an entity by id
     *
     * @param entity Class
     *
     * @author
     * @since
     */
    public List<T> findAllXId( BaseEntity entity )
    {
        final CriteriaBuilder criteriaBuilder;
        final CriteriaQuery<T> criteriaQuery;
        final Root<T> root;
        List<T> list = null;

        try
        {
            _em = _handler.getSession();
            criteriaBuilder = _em.getCriteriaBuilder();
            criteriaQuery = criteriaBuilder.createQuery( _entityClass );
            root = criteriaQuery.from( _entityClass );
            criteriaQuery.select( root ).where( criteriaBuilder.equal(
                    root.get( "_idFK" ), entity.getId() ) );


            list = _em.createQuery( criteriaQuery ).getResultList();
        }
        catch( LeCafeDBHandlerException e )
        {
            //region Instrumentation ERROR
            _logger.error( String.format( "{}, mensaje: {}, causa: {}", e.getMsg(), e.getMessage(),
                                          e.getCause() ), e );
            //endregion
            throw new LeCafeFindAllException( e, _entityClass.toString() ); //todo mejorar la excepcion
        }
        catch ( Exception e )
        {
            //region Instrumentaion ERROR
            _logger.error( String.format( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(),
                                          e.getCause()) );
            //endregion
            throw new LeCafeFindAllException( e, _entityClass.toString() ); //todo mejorar la excepcion
        }
        finally
        {
            _handler.closeSession();
        }

        return list;
    }

    /**
     * Name: find
     * Description: method for collecting an entity
     *
     * @param entity entity
     *
     * @author
     * @since
     */
    public T find( BaseEntity entity )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a BaseDAO.find: entidad {}", entity.toString() );
        //endregion

        final CriteriaBuilder criteriaBuilder;
        final CriteriaQuery<T> criteriaQuery;
        final Root<T> root;
        T result = null;

        try
        {
            _em = _handler.getSession();
            _em.getTransaction().begin();

            result = ( T ) _em.find( entity.getClass(), entity.getId() );

            _em.getTransaction().commit();

            _em.close();

        }
        catch( LeCafeDBHandlerException e )
        {
            //region Instrumentation ERROR
            _logger.error( String.format( "{}, mensaje: {}, causa: {}", e.getMsg(), e.getMessage(),
                                          e.getCause() ), e );
            //endregion
            throw new LeCafeFindException( e, _entityClass.toString() ); //todo mejorar la excepcion
        }
        catch ( Exception e )
        {
            //region Instrumentaion ERROR
            _logger.error( String.format( "tipo {}, mensaje {}, causa {}", e.getClass().getName(), e.getMessage(),
                                          e.getCause()) );
            //endregion
            throw new LeCafeFindException( e, _entityClass.toString() ); //todo mejorar la excepcion
        }
        finally
        {
            _handler.closeSession();
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de BaseDAO.find: lista {}", result.toString() );
        //endregion
        return result;
    }


    public List<T> findAll( Class<T> type )
    {
        _em = _handler.getSession();

        final CriteriaBuilder criteriaBuilder = _em.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery( type );
        final Root<T> root = criteriaQuery.from( type );
        final List<T> list;

        try
        {
            criteriaQuery.select( root );

            list = _em.createQuery( criteriaQuery ).getResultList();

        }
        catch ( Exception e )
        {
            _logger.error( e.getMessage(), e );
            throw new LeCafeFindException( e, type.toString() );
        }

        return list;
    }

    public DBHandler getDBHandler()
    {
        return _handler;
    }
}
