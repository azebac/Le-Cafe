package com.lecafe.logic.commands.mail;

import com.lecafe.common.email.Email;
import com.lecafe.common.utilities.Registry;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.Command;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceFileResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SendSOS extends Command<Boolean>
{
    private static ExecutorService _pool;
    private static Logger _logger = LoggerFactory.getLogger( SendSOS.class );

    private Email _email;

    static
    {
        _pool = Executors.newFixedThreadPool( Integer.valueOf(
                Registry.getInstance().getProperty( Registry.SMTP_SOS_POOL ) ) );
    }

    public SendSOS( Email email )
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando SendSOS.CTOR: mail {}", email );
        //endregion

        _email = email;

        //region Instrumentation DEBUG
        _logger.debug( "saliendo SendSOS.CTOR" );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a SendSOS.execute" );
        //endregion

        _pool.submit( this::run );

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de SendSOS.execute" );
        //endregion
    }

    private void run()
    {
        //region Instrumentation DEBUG
        _logger.debug( "entrando a SendSOS.run" );
        //endregion

        try
        {
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setHostName( Registry.getInstance().getProperty( Registry.SMTP_SOS_HOST_NAME ) );
            email.setSmtpPort( Integer.valueOf( Registry.getInstance().getProperty( Registry.SMTP_SOS_PORT ) ) );
            email.setAuthentication( Registry.getInstance().getProperty( Registry.SMTP_SOS_SENDER ),
                                     Registry.getInstance().getProperty( Registry.SMTP_SOS_SENDER_PASSWORD ) );
            email.setSSLOnConnect( true );
            email.setFrom( Registry.getInstance().getProperty( Registry.SMTP_SOS_SENDER ),
                           Registry.getInstance().getProperty( Registry.SMTP_SOS_NAME ) );

            for ( String to : _email.getTo() )
                email.addTo( to );

            email.setSubject( _email.getSubject() );
            email.setHtmlMsg( _email.getMessage() );
            email.setDataSourceResolver( new DataSourceFileResolver( new File(
                    Registry.getInstance().getProperty( Registry.RESOURCE_DIR ) ) ) );

            email.send();
        }
        catch ( Exception e )
        {
            _logger.error( e.getMessage(), e );
        }

        //region Instrumentation DEBUG
        _logger.debug( "saliendo de SendSOS.run" );
        //endregion
    }

    @Override
    public Boolean getReturnParam()
    {
        throw new UnsupportedOperationException(  );
    }
}
