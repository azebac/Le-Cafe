package com.lecafe.logic.commands.mail;

import com.lecafe.common.email.Email;
import com.lecafe.common.exceptions.mail.CreateMailException;
import com.lecafe.common.utilities.Registry;
import com.lecafe.logic.commands.Command;
import com.lecafe.logic.commands.Command;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateMail extends Command<Email>
{
    private static final String EXTENSION = ".ftl";

    private static Configuration _config;
    private static Logger _logger = LoggerFactory.getLogger( CreateMail.class );

    private Email _result;
    private Template _template;
    private List<String> _params;

    static
    {
        try
        {
            _config = new Configuration();
            _config.setDirectoryForTemplateLoading( new File(
                    Registry.getInstance().getProperty( Registry.TEMPLATE_DIR ) ) );
        }
        catch ( Exception e )
        {
            throw new CreateMailException( e, e.getMessage() );
        }
    }

    public CreateMail( List<String> params, Email email )
    {
        //Instrumentation DEBUG
        _logger.debug( "entrando a CreateMail.CTOR: params {}, mail {}", params, email );
        //endregion

        try
        {
            StringBuilder template = new StringBuilder( email.getType() )
                    .append( email.getLanguage() )
                    .append( EXTENSION );

            _template = _config.getTemplate( template.toString() );

            _params = params;
            _result = email;
        }
        catch( Exception e )
        {
            throw new CreateMailException( e, e.getMessage() );
        }

        //Instrumentation DEBUG
        _logger.debug( "saliendo de CreateMail.CTOR: _template {}", _template.toString() );
        //endregion
    }

    @Override
    public void execute()
    {
        Writer output;

        //Instrumentation DEBUG
        _logger.debug( "entrando a CreateMail.execute" );
        //endregion

        try
        {
            StringBuilder subject = new StringBuilder( _result.getType() ).append( _result.getLanguage() );

            Map<String, String> map = new HashMap<>();

            for ( int i = 0; i < _params.size(); i++ )
            {
                map.put( "v" + i, StringEscapeUtils.escapeHtml4( _params.get( i ) ) );
            }

            output = new StringWriter();
            _template.process( map, output );

            _result.setSubject( Registry.getInstance().getProperty( subject.toString() ) );
            _result.setMessage( output.toString() );
        }
        catch( Exception e )
        {
            throw new CreateMailException( e, e.getMessage() );
        }

        //Instrumentation DEBUG
        _logger.debug( "saliendo de CreateMail.execute: mail {}", _result );
        //endregion
    }

    @Override
    public Email getReturnParam()
    {
        return _result;
    }
}
