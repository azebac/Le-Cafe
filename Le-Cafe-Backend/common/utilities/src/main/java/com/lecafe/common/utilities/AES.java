package com.lecafe.common.utilities;

import com.lecafe.common.exceptions.aes.AESAlreadyDecryptException;
import com.lecafe.common.exceptions.aes.AESDecryptException;
import com.lecafe.common.exceptions.aes.AESEncryptException;
import com.lecafe.common.exceptions.aes.AESSetKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AES
{
    private static SecretKeySpec _secretKey;
    private static Logger _logger = LoggerFactory.getLogger( AES.class );

    static
    {
        setKey( Registry.getInstance().getProperty( Registry.AES_SECRET ) );
    }

    private static void setKey( String key )
    {
        final int length = 16;
        MessageDigest sha;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a AES.setKey: key {}", key );
        //endregion

        try
        {
            byte[] bytes = key.getBytes( StandardCharsets.UTF_8 );
            sha = MessageDigest.getInstance( "SHA-1" );
            bytes = sha.digest( bytes );
            bytes = Arrays.copyOf( bytes, length );
            _secretKey = new SecretKeySpec( bytes, "AES" );
        }
        catch( Exception e )
        {
            throw new AESSetKeyException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de AES.setKey" );
        //endregion
    }

    public static String encrypt( String strToEncrypt )
    {
        String response = strToEncrypt;

        if( strToEncrypt != null )
        {
            //region Instrumentation DEBUG
            _logger.debug( "entrando a AES.encrypt: String {}", strToEncrypt );
            //endregion

            try
            {
                decrypt( strToEncrypt );
            }
            catch ( AESAlreadyDecryptException e )
            {
                response = doEncrypt( strToEncrypt );
            }
            catch ( Exception e )
            {
                throw new AESEncryptException( e, e.getMessage() );
            }

            //region Instrumentation DEBUG
            _logger.debug( "saliendo de AES.encrypt: Response: {}", response );
            //endregion
        }

        return response;
    }

    private static String doEncrypt( String strToEncrypt )
    {
        String response;

        try
        {
            final Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );
            cipher.init( Cipher.ENCRYPT_MODE, _secretKey );
            response = Base64.getEncoder().encodeToString(
                    cipher.doFinal( strToEncrypt.getBytes( StandardCharsets.UTF_8 ) ) );
        }
        catch ( Exception e )
        {
            throw new AESEncryptException( e, e.getMessage() );
        }

        return response;
    }

    public static String decrypt( String strToDecrypt )
    {
        String response = null;

        if( strToDecrypt != null )
        {
            //region Instrumentation DEBUG
            _logger.debug( "entrando a AES.decrypt: String {}", strToDecrypt );
            //endregion

            try
            {
                final Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5PADDING" );
                cipher.init( Cipher.DECRYPT_MODE, _secretKey );
                response = new String( cipher.doFinal( Base64.getDecoder().decode( strToDecrypt ) ) );
            }
            catch ( IllegalBlockSizeException | IllegalArgumentException | BadPaddingException e )
            {
                throw new AESAlreadyDecryptException( e, e.getMessage() );
            }
            catch ( Exception e )
            {
                throw new AESDecryptException( e, e.getMessage() );
            }

            //region Instrumentation DEBUG
            _logger.debug( "saliendo de AES.decrypt: Response: {}", response );
            //endregion

        }

        return response;
    }
}
