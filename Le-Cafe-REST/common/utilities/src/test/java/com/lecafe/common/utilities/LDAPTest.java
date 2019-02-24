package com.lecafe.common.utilities;

import com.lecafe.common.EntityFactory;
import com.lecafe.common.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LDAPTest
{
    private User _entity;

    @BeforeEach
    void init()
    {
        createEntity();
    }

    void createEntity()
    {
        _entity = EntityFactory.createUser();
        _entity.setEmail( "wjoseperez20@gmail.com" );
        _entity.setPassword( "AidPassTest" );
    }

    @Test @Disabled
    public void testLdapUserAuthentication()
    {
        try
        {
            LDAP ldap = new LDAP();
            ldap.userAuthentication( _entity );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

    }

    @Test
    @Disabled
    void createUserLDAP()
    {
        User user = new User();
        user.setEmail( "bismarckpm2@gmail.com" );
        user.setPassword( "PruebaLDAP1234" );
        LDAP ldap = new LDAP();
        ldap.addEntryToLdap( user );
    }

    @Test
    @Disabled
    void deleteUserLDAP()
    {
        User user = new User();
        user.setEmail( "bismarckpm2@gmail.com" );
        LDAP ldap = new LDAP();
        ldap.deleteEntry( user );
    }

    @Test
    @Disabled
    void getUserLDAP()
    {
        User user = new User();
        user.setEmail( "bismarckpmpruebaLDAP@gmail.com" );
        LDAP ldap = new LDAP();
        ldap.getEntry( user );
    }

    @Test
    @Disabled
    void changePassword()
    {
        User user = new User();
        user.setEmail( "bismarckpmpruebaLDAP@gmail.com" );
        user.setPassword( "MARIAPEPE" );
        LDAP ldap = new LDAP();
        ldap.changePassword( user );
    }

}
