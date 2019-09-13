package com.lecafe.logic.commands;

import com.lecafe.common.entities.User;
import com.lecafe.logic.commands.activeDirectory.AuthenticateDirectory;
import com.lecafe.logic.commands.user.atomic.GetUserByEmail;
import com.lecafe.logic.commands.user.composite.AuthenticateUser;
import com.lecafe.logic.commands.user.composite.ChangePassword;
import com.lecafe.logic.commands.user.composite.RecoverPassword;
import com.lecafe.persistence.DBHandler;

public class CommandFactory
{
    public static GetUserByEmail createGetUserByEmail( User user, DBHandler handler )
    {
        return new GetUserByEmail( user, handler );
    }

    public static AuthenticateUser createAuthenticateUser( User user )
    {
        return new AuthenticateUser( user );
    }

    public static ChangePassword createChangePassword( User user )
    {
        return new ChangePassword( user );
    }

    public static RecoverPassword createRecoverPassword( User user )
    {
        return new RecoverPassword( user );
    }

    public static AuthenticateDirectory createAuthenticateDirectory( User user )
    {
        return new AuthenticateDirectory( user );
    }




}
