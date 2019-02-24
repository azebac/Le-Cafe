package com.lecafe.logic.commands;

public abstract class Command<R>
{
    public abstract void execute();

    public abstract R getReturnParam();

}

