package com.lecafe.persistence.dao;

import com.lecafe.common.entities.Gender;
import com.lecafe.common.entities.User;
import com.lecafe.common.enums.UserStatus;
import com.lecafe.common.enums.UserType;
import com.lecafe.persistence.DAOFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOTest
{

    @Test @Disabled
    void findAllTest()
    {
        UserDAO dao = DAOFactory.createUserDAO();
        List<User> list;

        list = dao.findAll();

        assertTrue( list.size() > 0 );

    }

    @Test @Disabled
    void insert()
    {
        UserDAO dao = DAOFactory.createUserDAO();
        User user = new User();
        user.setNames( "Antonio" );
        user.setLastnames( "Pernia" );
        user.setBirthdate( LocalDate.of( 1995, 3, 7 ) );
        user.setEmail( "antoniopernia@gmail.com" );
        user.setIdNumber( "9201283737" );
        Gender gender = new Gender( 5 );
        user.setGender( gender );
        dao.insert( user );
    }
}