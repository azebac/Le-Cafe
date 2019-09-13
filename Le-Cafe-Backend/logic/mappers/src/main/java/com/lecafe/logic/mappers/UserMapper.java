package com.lecafe.logic.mappers;

import com.lecafe.common.EntityFactory;
import com.lecafe.common.entities.User;
import com.lecafe.common.enums.UserStatus;
import com.lecafe.common.enums.UserType;
import com.lecafe.logic.dtos.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserMapper
{
    private static Logger _logger = LoggerFactory.getLogger( UserMapper.class );

    public static User mapDTOToEntity( UserDTO dto )
    {
        User entity = EntityFactory.createUser();

        //region Instrumentation
        _logger.debug( "entrando a UserMapper.mapDTOToEntity: dto {}", dto );
        //endregion

        entity.setId( dto._id );
        entity.setPassword( dto._password );
        entity.setNewPassword( dto._newPassword );


        if( dto._idNumber != null )
            entity.setIdNumber( dto._idNumber.toLowerCase() );

        if ( dto._email != null )
            entity.setEmail( dto._email.toLowerCase() );

        if ( dto._birthdate != null )
            entity.setBirthdate( LocalDate.parse( dto._birthdate ) );

        //region Instrumentation
        _logger.debug( "saliendo de UserMapper.mapDTOToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static UserDTO mapEntityToDTO( User entity )
    {
        final UserDTO dto = new UserDTO();

        //region Instrumentation
        _logger.debug( "entrando a UserMapper.mapEntityToDTO: entity {}", entity );
        //endregion

        entity.decrypt();

        dto._id = entity.getId();
        dto._idNumber = entity.getIdNumber();
        dto._email = entity.getEmail();

        if ( entity.getBirthdate() != null )
            dto._birthdate = entity.getBirthdate().toString();

        if ( entity.getGender() != null )
            dto._gender = entity.getGender().getId();


        //region Instrumentation
        _logger.debug( "saliendo de UserMapper.mapEntityToDTO: dto {}", dto );
        //endregion

        return dto;
    }

    public static List<UserDTO> mapEntityListToDTOList( List<User> entityList )
    {
        final List<UserDTO> dtoList = new ArrayList<>();

        for ( User entity : entityList )
            dtoList.add( mapEntityToDTO( entity ) );

        return dtoList;
    }

    public static List<User> mapDTOListToEntityList( List<UserDTO> dtoList )
    {
        final List<User> entityList = new ArrayList<>();

        for ( UserDTO dto : dtoList )
            entityList.add( mapDTOToEntity( dto ) );

        return entityList;
    }

}
