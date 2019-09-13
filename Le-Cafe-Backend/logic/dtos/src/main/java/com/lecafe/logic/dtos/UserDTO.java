package com.lecafe.logic.dtos;

public class UserDTO
{
    public long _id;
    public String _name;
    public String _lastName;
    public String _birthdate;
    public String _idNumber;
    public String _email;
    public String _picture;
    public String _miniPicture;
    public String _token;
    public String _firebase;
    public String _timeZone;
    public String _password;
    public String _newPassword;
    public String _languageCode;
    public long _gender;
    public long _language;
    public long _civilStatus;
    public int _userType;
    public int _userStatus;

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "UserDTO{" );
        sb.append( "_id=" ).append( _id );
        sb.append( '}' );
        return sb.toString();
    }
}
