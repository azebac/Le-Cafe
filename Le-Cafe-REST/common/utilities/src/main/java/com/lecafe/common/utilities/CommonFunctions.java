package com.lecafe.common.utilities;

import java.util.Random;
import java.util.UUID;

public class CommonFunctions
{

    public static String generatePassword()
    {
        String password;
        StringBuilder temporary = new StringBuilder();
        Random random = new Random();

        while (temporary.length() <  Integer.parseInt(ReaderProperties.getValue
            ( "temporaryKeyLength", "Logic.properties")))
        {
            int index = ( int ) ( random.nextFloat() * ReaderProperties.getValue
                    ( "temporalKeyCharacters", "Logic.properties").length() );
            temporary.append( ReaderProperties.getValue
                    ( "temporalKeyCharacters", "Logic.properties").charAt( index ) );
        }

        password = temporary.toString();

        return password;
    }

    public static String generatedPin()
    {
        UUID id = UUID.randomUUID();
        return id.toString();
    }
}
