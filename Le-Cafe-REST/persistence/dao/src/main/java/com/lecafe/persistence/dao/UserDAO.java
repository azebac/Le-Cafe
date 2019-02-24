package com.lecafe.persistence.dao;

import com.lecafe.common.entities.User;
import com.lecafe.persistence.DBHandler;
import com.lecafe.common.utilities.LDAP;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO<User>
{

    @PersistenceContext
    private EntityManager _em;
    private DBHandler _handler;

    public User validateRegisteredUser( User user ) throws Exception
    {
        User result = new User();
        final CriteriaBuilder criteriaBuilder;
        final CriteriaQuery<User> criteriaQuery;
        final Root<User> root;
        try
        {
            _em = _handler.getSession();
            criteriaBuilder = _em.getCriteriaBuilder();
            criteriaQuery = criteriaBuilder.createQuery( User.class );
            root = criteriaQuery.from( User.class );
            criteriaQuery.select( root );
            final List<Predicate> and = new ArrayList<>();
            and.add( criteriaBuilder.equal( root.get( "email" ), user.getEmail() ) );
            criteriaQuery.where( and.toArray( new Predicate[]{} ) );
            result = _em.createQuery( criteriaQuery ).getSingleResult();
            if ( result == null )
            {
                throw new Exception( "Unregistered user" );
            }
        }
        catch ( Exception e )
        {
            throw e;
        }
        return result;
    }

    public void changeUserPassword( User user )
    {
        LDAP ldap = new LDAP();
        try
        {
            ldap.changePassword( user );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public User userAuthentication( User user )
    {
        User result = new User();
        LDAP ldap = new LDAP();

        try
        {
            ldap.userAuthentication( user );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        return result;
    }
}
