package com.lecafe.services.implementations;



import com.lecafe.common.utilities.Registry;
import com.lecafe.persistence.dao.ActiveDirectoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath( "/api" )
public class BaseApplicationService extends Application
{
    private static Logger _logger = LoggerFactory.getLogger( ActiveDirectoryDAO.class );


    @Override
    public Set<Class<?>> getClasses()
    {
        final HashSet hash = new HashSet<Class<?>>();

        Registry.getInstance(); // this automatically loads the properties file

        hash.add(UserService.class);
        return hash;
    }


}

