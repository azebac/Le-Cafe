package com.lecafe.services.implementations;

import com.lecafe.common.utilities.Registry;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BaseApplicationServiceTest
{

    @Test
    @Disabled
    public void getClassesTest()
    {
        BaseApplicationService base = new BaseApplicationService();
        base.getClasses();

        assertNotNull( Registry.getInstance() );
    }
}
