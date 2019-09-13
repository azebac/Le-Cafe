package com.lecafe.common.utilities;

import com.lecafe.common.exceptions.jwt.JWTCreateException;
import com.lecafe.common.exceptions.jwt.JWTSetKeyException;
import com.lecafe.common.exceptions.jwt.JWTVerifyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JWT
{
    private static SecretKey _secretKey;
    private static String _issuer = Registry.getInstance().getProperty( Registry.JWT_ISSUER );
    private static int _expiration = Integer.valueOf( Registry.getInstance().getProperty( Registry.JWT_EXPIRATION ) );

    private static Logger _logger = LoggerFactory.getLogger( JWT.class );

    static
    {
        setKey( Registry.getInstance().getProperty( Registry.JWT_SECRET ) );
    }

    private static void setKey( String key )
    {
        MessageDigest sha;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a JWT.setKey: key {}", key );
        //endregion

        try
        {
            byte[] bytes = key.getBytes( StandardCharsets.UTF_8 );
            sha = MessageDigest.getInstance( "SHA-256" );

            _secretKey = Keys.hmacShaKeyFor( sha.digest( bytes ) );
        }
        catch( Exception e )
        {
            throw new JWTSetKeyException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de JWT.setKey" );
        //endregion
    }

    public static String createToken( String subject, String audience )
    {
        String result;

        //region Instrumentation DEBUG
        _logger.debug( "entrando a JWT.createToken: subject {} audience {}", subject, audience );
        //endregion

        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( new Date(  ) );
            calendar.add( Calendar.DAY_OF_YEAR, _expiration );

            result = Jwts.builder()
            .setIssuer( _issuer )
            .setSubject( subject )
            .setAudience( audience )
            .setExpiration( calendar.getTime() )
            .setNotBefore( new Date(  ) )
            .setIssuedAt( new Date(  ) )
            .setId( UUID.randomUUID().toString() )
            .signWith( _secretKey, SignatureAlgorithm.HS256 )
            .compact();
        }
        catch ( Exception e )
        {
            throw new JWTCreateException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo a JWT.createToken: token {}", result );
        //endregion

        return result;
    }

    public static List<String> verifyToken( String token )
    {
        List<String> result = new ArrayList<>(  );

        //region Instrumentation DEBUG
        _logger.debug( "entrando a JWT.createToken: token {}", token );
        //endregion

        try
        {
            Jws<Claims> claims = Jwts.parser()
                    .requireIssuer( _issuer )
                    .setSigningKey( _secretKey )
                    .parseClaimsJws( token );

            result.add( claims.getBody().getSubject() );
            result.add( claims.getBody().getAudience() );
        }
        catch ( Exception e )
        {
            throw new JWTVerifyException( e, e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo a JWT.createToken: result {}", result );
        //endregion

        return result;
    }
}
