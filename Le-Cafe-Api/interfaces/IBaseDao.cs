using System.Collections.Generic;

namespace interfaces
{
    public interface IBaseDao<T>
    {
        T Add(T entity);

        T Edit(T entity);

        IList<T> GetAll();

        T GetById(long id);

        T Delete(T entity);
    }
}