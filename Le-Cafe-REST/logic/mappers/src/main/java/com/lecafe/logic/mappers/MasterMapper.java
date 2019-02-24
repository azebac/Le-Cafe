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


    public static List<MasterDTO> mapEntityListToDTOList( List<MasterEntity> masterEntities )
    {
        final List<MasterDTO> dtoList;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a MasterMapper.mapEntityListToDTOList: size {}, lista {}", masterEntities.size(),
            masterEntities.toString() );
        //endregion

        dtoList = new ArrayList<>();

        for ( MasterEntity tempE : masterEntities )
        {
            final MasterDTO tempD = mapEntityToDTO( tempE );
            dtoList.add( tempD );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de MasterMapper.mapEntityListToDTOList: size {}, lista {}", dtoList.size(),
            dtoList.toString() );
        //endregion

        return dtoList;
    }


    public static MasterDTO mapEntityToDTO( MasterEntity entity )
    {
        final MasterDTO dto;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a MasterMapper.mapEntityToDTO: entity {}", entity.toString() );
        //endregion

        dto = new MasterDTO();

        dto._id = entity.getId();
        dto._value = entity.getValue();
        dto._status = entity.getStatus();

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de MasterMapper.mapEntityToDTO: dto {}", dto.toString() );
        //endregion

        return dto;
    }


    public MasterEntity mapDTOToEntity( MasterDTO dto, MasterEntity entity )
    {
        //MasterEntity temp = EntityFactory.create
        throw new UnsupportedOperationException();
    }


}

