package com.lecafe.logic.mappers;

import com.lecafe.common.EntityFactory;
import com.lecafe.common.entities.User;
import com.lecafe.logic.dtos.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class UserMapper
{
    private static Logger _logger = LoggerFactory.getLogger( UserMapper.class );

    public static User mapDTOToEntity( UserDTO dto )
    {
        User entity = EntityFactory.createUser();

        //region Instrumentation
        _logger.debug( "entrando a mapDTOToEntity: dto {}", dto.toString() );
        //endregion

        entity.setNames( dto.names);
        entity.setLastnames( dto.lastnames);
        entity.setBirthdate( LocalDate.parse( dto.birthdate) );
        entity.setEmail( dto.email);
        entity.setPassword( dto.password);
        entity.setPoints(dto.points);


        //region Instrumentation
        _logger.debug( "saliendo de mapDTOToEntity: entity {}", entity.toString() );
        //endregion

        return entity;
    }


    public static UserDTO mapEntityToDTO( User entity )
    {
        final UserDTO dto = new UserDTO();

        //region Instrumentation
        _logger.debug( "entrando a mapEntityToDTO: entity {}", entity.toString() );
        //endregion

        dto.names = entity.getNames();
        dto.lastnames = entity.getLastnames();
        dto.birthdate = entity.getBirthdate().toString();
        dto.email =  entity.getEmail();
        dto.password = entity.getPassword();
        dto.points = entity.getPoints();

        //region Instrumentation
        _logger.debug( "saliendo de mapEntityToDTO: dto {}", dto.toString() );
        //endregion

        return dto;
    }

    public static List<UserDTO> mapEntityListToDTOList( List<User> users )
    {
        throw new UnsupportedOperationException();
    }


    public static List<User> mapDTOListToEntityList( List<UserDTO> entityList )
    {
        throw new UnsupportedOperationException();
    }
}
