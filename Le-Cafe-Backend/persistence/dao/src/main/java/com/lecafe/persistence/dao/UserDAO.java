package com.lecafe.persistence.dao;

import com.lecafe.common.entities.User;
import com.lecafe.common.exceptions.jpa.FindException;
import com.lecafe.common.exceptions.jpa.NotFoundException;
import com.lecafe.common.exceptions.jpa.UpdateException;
import com.lecafe.persistence.DBHandler;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

public class UserDAO extends BaseDAO<User>
{

    private EntityManager _em;
    private CriteriaBuilder _builder;

    public UserDAO( DBHandler handler )
    {
        super( handler );

        _em = getDBHandler().getSession();
        _builder = _em.getCriteriaBuilder();
    }

    public User findByEmail( User user )
    {
        User result;

        try
        {
            CriteriaQuery<User> query = _builder.createQuery( User.class );
            Root<User> root = query.from( User.class );

            query.select( root );
            query.where( _builder.equal( root.get( "_email" ), user.getEmail() ) );

            result = _em.createQuery( query ).getSingleResult();
        }
        catch ( NoResultException e )
        {
            throw new NotFoundException( e, e.getMessage() );
        }
        catch ( Exception e )
        {
            throw new FindException( e, e.getMessage() );
        }

        return result;
    }

    public User findByIdNumber( User user )
    {
        User result;

        try
        {
            CriteriaQuery<User> query = _builder.createQuery( User.class );
            Root<User> root = query.from( User.class );

            query.select( root );
            query.where( _builder.equal( root.get( "_idNumber" ), user.getIdNumber() ) );

            result = _em.createQuery( query ).getSingleResult();
        }
        catch ( NoResultException e )
        {
            throw new NotFoundException( e, e.getMessage() );
        }
        catch ( Exception e )
        {
            throw new FindException( e, e.getMessage() );
        }

        return result;
    }


}
