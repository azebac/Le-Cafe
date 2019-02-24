package com.lecafe.logic.mappers;

import com.lecafe.common.EntityFactory;
import com.lecafe.common.entities.User;
import com.lecafe.logic.dtos.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserMapperTest
{
    private UserDTO _dto;
    private User _entity;

    @BeforeEach
    void init()
    {
        createDTO();
        createEntity();

    }

    void createEntity()
    {
        _entity = EntityFactory.createUser();
        _entity.setNames( "Wilmer Jose" );
        _entity.setLastnames( "Perez Diaz" );
        _entity.setEmail( "wjoseperez20@gmail.com" );
        _entity.setBirthdate( LocalDate.of( 1995, 3, 7 ) );
        _entity.setIdNumber( "V25420700" );
    }


    void createDTO()
    {
        _dto = new UserDTO();
        _dto.names =  "Wilmer Jose";
        _dto.lastnames = "Perez Diaz";
        _dto.email =  "wjoseperez20@gmail.com";
        _dto.birthdate =  LocalDate.of( 1995, 3, 7 ).toString();
    }

    @Test
    @Disabled
    void testMapDTOToEntity()
    {
        User result;

        result = ( new UserMapper() ).mapDTOToEntity( _dto );

        assertNotNull( result );
        assertTrue( result.equals( _entity ) );
    }


    @Test
    @Disabled
    void testMapEntityToDTO()
    {
        UserDTO result;

        result = ( new UserMapper() ).mapEntityToDTO( _entity );

        assertNotNull( result );
        assertTrue( result.equals( _dto ) );
    }
}
