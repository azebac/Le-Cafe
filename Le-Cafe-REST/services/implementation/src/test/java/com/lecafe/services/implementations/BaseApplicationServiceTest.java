package com.lecafe.services.implementations;

import com.lecafe.common.utilities.Registry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseApplicationServiceTest
{

    @Test
    public void getClassesTest()
    {
        BaseApplicationService base = new BaseApplicationService();
        base.getClasses();

        assertTrue( Registry.getInstance().isPropertiesLoaded() );

        base.getClasses();
    }
}
