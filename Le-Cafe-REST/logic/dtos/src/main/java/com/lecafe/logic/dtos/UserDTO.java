package com.lecafe.logic.dtos;


public class UserDTO
{
    //region Attributes
    public long id;
    public String _value;
    public byte _status;
    public String names;
    public String lastnames;
    public String birthdate;
    public String email;
    public String password;
    public String newPassword;
    public int _userStatus;
    public int gender;
    public int points;


    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "UserDTO{" );
        sb.append( "id=" ).append(id);
        sb.append( ", _value='" ).append( _value ).append( '\'' );
        sb.append( ", _status=" ).append( _status );
        sb.append( ", names='" ).append(names).append( '\'' );
        sb.append( ", lastnames='" ).append(lastnames).append( '\'' );
        sb.append( ", birthdate='" ).append(birthdate).append( '\'' );
        sb.append( ", email='" ).append(email).append( '\'' );
        sb.append( ", password='" ).append(password).append( '\'' );
        sb.append( ", newPassword='" ).append(newPassword).append( '\'' );
        sb.append( ", _userStatus=" ).append( _userStatus );
        sb.append( ", gender=" ).append(gender);
        sb.append( '}' );
        return sb.toString();
    }
}
