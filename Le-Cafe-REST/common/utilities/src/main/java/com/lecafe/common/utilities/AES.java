package com.lecafe.common.utilities;

import com.lecafe.common.exceptions.LeCafAESDecryptException;
import com.lecafe.common.exceptions.LeCafeAESEncryptException;
import com.lecafe.common.exceptions.LeCafeAESSetKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AES
{
    private static SecretKeySpec _secretKey;
    private static byte[] _key;

    private static Logger _logger = LoggerFactory.getLogger( AES.class );


    public static void setKey( String myKey )
    {
        final int length = 16;
        MessageDigest sha = null;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a AES.setKey: key {}", myKey );
        //endregion

        try
        {
            _key = myKey.getBytes( "UTF-8" );
            sha = MessageDigest.getInstance( "SHA-1" );
            _key = sha.digest( _key );
            _key = Arrays.copyOf( _key, length );
            _secretKey = new SecretKeySpec( _key, "AES" );
        }
        catch( Exception e )
        {
            _logger.error( "mensaje: {} \n causa: {}", e.getMessage(), e.getCause(), e );
            throw new LeCafeAESSetKeyException( e );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de AES.setKey" );
        //endregion
    }


    public static String encrypt( String strToEncrypt )
    {
        return encrypt( strToEncrypt, Registry.getInstance().getProperty( "aes.secret" ) );
    }


    private static String encrypt( String strToEncrypt, String secret )
    {
        final String response;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a AES.encrypt: mensaje {}", strToEncrypt );
        //endregion

        try
        {
            setKey( secret );
            final Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );
            cipher.init( Cipher.ENCRYPT_MODE, _secretKey );
            response = Base64.getEncoder().encodeToString(
                    cipher.doFinal( strToEncrypt.getBytes( "UTF-8" ) ) );

            //region Instrumentation INFO
            _logger.info( "encrypt ejecutado: mensaje: {}, respuesta: {}", strToEncrypt, response );
            //endregion
        }
        catch ( Exception e )
        {
            _logger.error( "mensaje: {} \n causa: {}", e.getMessage(), e.getCause(), e );
            throw new LeCafeAESEncryptException( e );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de AES.encrypt: respuesta: {}", response );
        //endregion

        return response;
    }



    public static String decrypt( String strToEncrypt )
    {
        return decrypt( strToEncrypt, Registry.getInstance().getProperty( "aes.secret" ) );
    }


    public static String decrypt( String strToDecrypt, String secret )
    {

        final String response;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a AES.decrypt: mensaje {}", strToDecrypt );
        //endregion

        try
        {
            setKey( secret );
            final Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5PADDING" );
            cipher.init( Cipher.DECRYPT_MODE, _secretKey );
            response = new String( cipher.doFinal( Base64.getDecoder().decode( strToDecrypt ) ) );

            //region Instrumentation INFO
            _logger.info( "decrypt ejecutado: mensaje: {}, respuesta: {}", strToDecrypt, response );
            //endregion
        }
        catch ( Exception e )
        {
            _logger.error( "mensaje: {} \n causa: {}", e.getMessage(), e.getCause(), e );
            throw new LeCafAESDecryptException( e );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de AES.decrypt: respuesta: {}", response );
        //endregion

        return response;
    }

}
