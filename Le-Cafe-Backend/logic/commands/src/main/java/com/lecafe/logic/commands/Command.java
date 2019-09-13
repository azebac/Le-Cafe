package com.lecafe.logic.commands;

import com.lecafe.common.email.Email;
import com.lecafe.common.entities.User;
import com.lecafe.common.enums.WeekDay;
import com.lecafe.persistence.DBHandler;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Command<T>
{
    private DBHandler _handler;

    public Command()
    {
    }

    public Command( DBHandler handler )
    {
        _handler = handler;
    }

    public abstract void execute();

    public abstract T getReturnParam();

    /**
     * Name: closeSession
     * Description: Commit changes and close database session
     */
    public void closeSession()
    {
        _handler.finishTransaction();
        _handler.closeSession();
    }

    /**
     * Name: createSession
     * Description: Create a database session
     *
     * @param transaction start a transaction (only for INSERT, UPDATE and DELETE)
     */
    protected void createSession( boolean transaction )
    {
        _handler = new DBHandler();

        if( transaction )
            _handler.beginTransaction();
    }

    /**
     * Name: getType
     * Description: Getter of database handler
     *
     * @return JPA handler
     */
    protected DBHandler getHandler()
    {
        return _handler;
    }

    /**
     * Name: createEmail
     * Description: Create an email object
     *
     * @param user user data
     * @param template email template
     *
     * @return email object
     */
    protected Email createEmail( User user, String template )
    {
        Email email = new Email();
        email.setType( template );
        email.setLanguage( "es" );
        email.getTo().add( user.getEmail() );

        return email;
    }





}
