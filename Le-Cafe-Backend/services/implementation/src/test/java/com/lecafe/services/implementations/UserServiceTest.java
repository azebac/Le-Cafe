package com.lecafe.services.implementations;

import com.lecafe.logic.dtos.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest
{
    private final static String AUTHORIZATION = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBaWRQYXNzIiwic3ViIjoiUEFUSUV" +
                                                "OVCIsImF1ZCI6IjEiLCJleHAiOjE1ODgxNzY0NjR9.1HU9q6hNXK9gr6QR6I" +
                                                "cc2hG3YsQPxNcOSnDLt5KyzWI";

    private UserService _service;
    private UserDTO _dto;

    @BeforeEach
    public void setUp()
    {
        _service = new UserService();
        _dto = new UserDTO();
    }

    @Test
    @Disabled
    public void validateEmailTest()
    {
        _dto._email = "felix.morales@gmail.com";

        assertTrue( _service.validateEmail( _dto ) );
    }

    @Test
    @Disabled
    public void validateIdentificationTest()
    {
        _dto._idNumber = "free_id_number";

        assertTrue( true );
    }

    @Test
    @Disabled
    public void authenticateUserTest()
    {
        _dto._email = "paciente2@yopmail.com";
        _dto._password = "123456";
        _dto._language = 2;
        _dto._languageCode = "ES";
        _dto._firebase = "123-random-hash-123";
        _dto._timeZone = ZoneId.systemDefault().toString();

        _service.authenticateUser( _dto );
    }

    @Test
    @Disabled
    public void changePasswordTest()
    {
        _dto._id = 1;
        _dto._password = "effhheef";
        _dto._newPassword = "effhheef";

        _service.changePassword( AUTHORIZATION, _dto );
    }

    @Test
    @Disabled
    public void recoverPasswordTest()
    {
        _dto._email = "felix.morales@gmail.com";

        _service.recoverPassword( _dto );
    }

    @Test
    @Disabled
    public void updateLanguageTest()
    {
        _dto._id = 1;
        _dto._language = 2;
        _dto._languageCode = "ES";

        //_service.updateLanguage( AUTHORIZATION, _dto );
    }

    @Test
    @Disabled
    public void updateConfigurationTest()
    {
        _dto._id = 1;
        _dto._language = 2;
        _dto._languageCode = "ES";
        _dto._firebase = "123-random-hash-123";
        _dto._timeZone = ZoneId.systemDefault().toString();

        //_service.updateConfiguration( AUTHORIZATION, _dto );
    }
}
