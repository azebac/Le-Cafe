package com.lecafe.common.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AESTest
{
    @Mock
    private Registry registry;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.initMocks( this );
    }


    @Test
    void testEncrypt()
    {
        String mensaje = "esto es una prueba";

        when( registry.getProperty( "aes.secret" ) ).thenReturn( "secretoPrueba" );

        String resultado = AES.encrypt( mensaje );

        // Verify the results
        assertNotNull( resultado );
        assertTrue( resultado.equals( "7bS8ZwaLyHy18ypUZvtmpA35aSVuIXSBHnNC5MfdPCE=" ) );
    }


    @Test
    void testDecrypt()
    {
        String mensaje = "7bS8ZwaLyHy18ypUZvtmpA35aSVuIXSBHnNC5MfdPCE=";

        when( registry.getProperty( "aes.secret" ) ).thenReturn( "secretoPrueba" );

        String resultado = AES.decrypt( mensaje );

        // Verify the results
        assertNotNull( resultado );
        assertTrue( resultado.equals( "esto es una prueba" ) );
    }
}
