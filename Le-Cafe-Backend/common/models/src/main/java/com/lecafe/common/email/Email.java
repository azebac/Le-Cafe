package com.lecafe.common.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Email
{
    private String _type;
    private String _language;
    private String _subject;
    private String _message;

    private List<String> _to;

    public Email()
    {
        _to = new ArrayList<>();
    }

    public String getType()
    {
        return _type;
    }

    public void setType( String type )
    {
        _type = type;
    }

    public String getLanguage()
    {
        return _language;
    }

    public void setLanguage( String language )
    {
        _language = language;
    }

    public String getSubject()
    {
        return _subject;
    }

    public void setSubject( String subject )
    {
        _subject = subject;
    }

    public String getMessage()
    {
        return _message;
    }

    public void setMessage( String message )
    {
        _message = message;
    }

    public List<String> getTo()
    {
        return _to;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        Email email = ( Email ) o;
        return Objects.equals( _subject, email._subject ) && Objects.equals( _message, email._message ) &&
               Objects.equals( _language, email._language) && Objects.equals( _to, email._to ) &&
               Objects.equals( _type, email._type );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( _subject, _message, _to );
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "Email{" );
        sb.append( "_type='" ).append( _type ).append( '\'' );
        sb.append( ", _language='" ).append( _language ).append( '\'' );
        sb.append( ", _subject='" ).append( _subject ).append( '\'' );
        sb.append( ", _message='" ).append( _message ).append( '\'' );
        sb.append( ", _to=" ).append( _to );
        sb.append( '}' );
        return sb.toString();
    }
}
