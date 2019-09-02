
public abstract class Command<T>
{
    public abstract T Param { get; set; }
    public abstract void Execute();

}
