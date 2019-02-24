package com.lecafe.persistence;


import com.lecafe.persistence.dao.GenderDAO;
import com.lecafe.persistence.dao.UserDAO;

public class DAOFactory
{



    public static GenderDAO createGenderDAO()
    {
        return new GenderDAO();

    }

    public static UserDAO createUserDAO()
    {
        return new UserDAO();

    }

}
