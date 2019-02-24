package com.lecafe.logic.commands;

import com.lecafe.common.entities.User;
import com.lecafe.logic.commands.user.AuthenticateUserCommand;
public class CommandFactory
{


    public static AuthenticateUserCommand createAuthenticateUserCommand(User user)
    {
        return new AuthenticateUserCommand(user);
    }

}
