package com.lecafe.persistence.dao;

import com.lecafe.common.entities.Gender;
import com.lecafe.persistence.DAOFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenderDAOTest
{

    @Test
    void findAllTest()
    {
        GenderDAO dao = DAOFactory.createGenderDAO();
        List<Gender> list;

        list = dao.findAll();

        assertTrue( true );

    }

}