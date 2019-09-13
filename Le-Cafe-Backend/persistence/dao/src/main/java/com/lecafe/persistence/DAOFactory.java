package com.lecafe.persistence;

import com.lecafe.persistence.dao.ActiveDirectoryDAO;
import com.lecafe.persistence.dao.UserDAO;

public class DAOFactory
{
    public static ActiveDirectoryDAO createActiveDirectoryDAO()
    {
        return new ActiveDirectoryDAO();
    }

    public static UserDAO createUserDAO( DBHandler handler )
    {
        return new UserDAO( handler );
    }


}
