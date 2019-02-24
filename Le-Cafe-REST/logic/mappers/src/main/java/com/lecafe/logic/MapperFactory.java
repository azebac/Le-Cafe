package com.lecafe.logic;


import com.lecafe.logic.mappers.MasterMapper;
import com.lecafe.logic.mappers.UserMapper;

public class MapperFactory
{


    private static MasterMapper createMasterMapper()
    {
        return new MasterMapper();
    }

    public static UserMapper createUserMapper()
    {
        return new UserMapper();
    }


}
