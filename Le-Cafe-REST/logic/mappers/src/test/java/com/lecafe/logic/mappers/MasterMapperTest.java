package com.lecafe.logic.mappers;


import com.lecafe.common.entities.Gender;
import com.lecafe.common.entities.MasterEntity;
import com.lecafe.logic.dtos.MasterDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterMapperTest
{
    private List<MasterEntity> _entityList;
    private List<MasterDTO> _dtoList;
    private MasterEntity _entity;
    private MasterDTO _dto;


    @BeforeEach
    void setUp()
    {

        _entity = createEntity();
        _dto = createDTO();

        _entityList = new ArrayList<>();
        _entityList.add( _entity );
        _entityList.add( _entity );
        _entityList.add( _entity );
        _entityList.add( _entity );

        _dtoList = new ArrayList<>();
        _dtoList.add( _dto );
        _dtoList.add( _dto );
        _dtoList.add( _dto );
        _dtoList.add( _dto );
    }

    private MasterDTO createDTO()
    {
        MasterDTO resultDTO = new MasterDTO();


        resultDTO._id = 4 ;
        resultDTO._value = "FEMENINO";

        return resultDTO;
    }

    private MasterEntity createEntity()
    {
        Gender temp = new Gender();

        temp.setId( 4 );
        temp.setValue( "FEMENINO" );


        return temp;
    }


    @AfterEach
    void tearDown()
    {
        _entityList = null;
        _dtoList = null;
        _entity = null;
        _dto = null;
    }

    @Test
    void testMapEntityToDTO()
    {
        // Run the test
        final MasterDTO result = MasterMapper.mapEntityToDTO( _entity );


        assertEquals( _dto._id, result._id );
        assertEquals( _dto._value, result._value );

    }


    @Test
    void testMapEntityListToDTOList()
    {
        // Run the test
        List<MasterDTO> result = MasterMapper.mapEntityListToDTOList( _entityList );

        // Verify the results
        assertEquals( _entityList.size(), result.size() );
    }


}
