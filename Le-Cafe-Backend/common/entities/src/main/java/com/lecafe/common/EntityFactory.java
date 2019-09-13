package com.lecafe.common;

import com.lecafe.common.entities.User;


public class EntityFactory
{

    public static User createUser()
    {
        return new User();
    }

    public static User createUser( long id )
    {
        return new User( id );
    }

}
