package com.lecafe.logic.commands;

import com.lecafe.common.entities.User;
import com.lecafe.logic.commands.user.ValidateRegisteredUserCommand;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateRegisteredCommandTest
{
    @Test
    @Disabled
    public void executeTest()
    {
        User user = new User();
        user.setNames( "Bismarck" );
        user.setLastnames( "Ponce" );
        user.setEmail( "bismarckpm@gmail.com" );
        ValidateRegisteredUserCommand command = new ValidateRegisteredUserCommand( user );
        command.execute();
        assertEquals(command.getReturnParam().getId(),"5" );
    }
}
