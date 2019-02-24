package com.lecafe.common.utilities;

import com.lecafe.common.entities.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LDAPTest
{

    @Test @Disabled
    void createUserLDAP()
    {
        User user = new User();
        user.setEmail( "bismarckpm23213213@gmail.com" );
        user.setPassword( "PruebaLDAP1234" );
        LDAP ldap = new LDAP();
        ldap.addEntryToLdap( user );
    }

    @Test @Disabled
    void deleteUserLDAP()
    {
        User user = new User();
        user.setEmail( "bismarckpm2@gmail.com" );
        LDAP ldap = new LDAP();
        ldap.deleteEntry( user );
    }

    @Test @Disabled
    void getUserLDAP()
    {
        User user = new User();
        user.setEmail( "bismarckpmpruebaLDAP@gmail.com" );
        LDAP ldap = new LDAP();
        ldap.getEntry( user );
    }

    @Test @Disabled
    void changePassword()
    {
        User user = new User();
        user.setEmail( "bismarckpmpruebaLDAP@gmail.com" );
        user.setPassword( "MARIAPEPE" );
        LDAP ldap = new LDAP();
        ldap.changePassword( user );
    }

    @Test @Disabled
    void userAuthentication()
    {
        User user = new User();
        user.setEmail( "bismarckpmpruebaLDAP@gmail.com" );
        user.setPassword( "MARIAPEPE" );
        LDAP ldap = new LDAP();
        ldap.userAuthentication( user );
    }

}
