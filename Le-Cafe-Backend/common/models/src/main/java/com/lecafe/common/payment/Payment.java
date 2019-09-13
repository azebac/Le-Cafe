package com.lecafe.common.payment;

public class Payment
{
    private String _token;
    private double _amount;

    public Payment()
    {

    }

    public Payment(String token, double amount)
    {
        _token = token;
        _amount = amount;
    }

    public String getToken()
    {
        return _token;
    }

    public void setToken( String token )
    {
        _token = token;
    }

    public double getAmount()
    {
        return _amount;
    }

    public void setAmount( double amount )
    {
        _amount = amount;
    }
}
