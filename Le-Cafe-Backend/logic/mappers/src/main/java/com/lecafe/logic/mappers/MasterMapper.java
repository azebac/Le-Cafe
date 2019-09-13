package com.lecafe.logic.mappers;

import com.lecafe.common.entities.MasterEntity;
import com.lecafe.logic.dtos.MasterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class MasterMapper
{
    private static Logger _logger = LoggerFactory.getLogger( MasterMapper.class );


    public static List<MasterDTO> mapEntityListToDTOList( List<MasterEntity> entityList )
    {
        final List<MasterDTO> dtoList = new ArrayList<>();

        //region Instrumentation DEBUG
        _logger.debug( "entrando a MasterMapper.mapEntityListToDTOList: size {}, lista {}",
                       entityList.size(), entityList );
        //endregion

        for ( MasterEntity entity : entityList )
            dtoList.add( mapEntityToDTO( entity ) );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de MasterMapper.mapEntityListToDTOList: size {}, lista {}",
                       dtoList.size(), dtoList );
        //endregion

        return dtoList;
    }


    public static MasterDTO mapEntityToDTO( MasterEntity entity )
    {
        final MasterDTO dto = new MasterDTO();

        //region Instrumentation DEBUG
        _logger.debug( "entrando a MasterMapper.mapEntityToDTO: entity {}", entity );
        //endregion

        dto._id = entity.getId();
        dto._value = entity.getValue();

        if(entity.getStatus() != null)
            dto._status = entity.getStatus().getValue();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de MasterMapper.mapEntityToDTO: dto {}", dto );
        //endregion

        return dto;
    }

}
