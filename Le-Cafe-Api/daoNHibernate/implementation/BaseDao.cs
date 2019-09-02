using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Reflection;
using interfaces;
using log4net;
using NHibernate;
using NHibernate.Criterion;
using NHibernate.Exceptions;

namespace daoNHibernate.implementation
{
    public abstract class BaseDao<T> : IBaseDao<T>
    {
        private static readonly ILog _log = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);
        protected ISession _session;

        protected BaseDao(ISession session)
        {
            #region instrumentation

            _log.DebugFormat("entrando ctor: session {0}", session);

            #endregion

            _session = session;

            #region instrumentation

            _log.Debug("saliendo ctor");

            #endregion
        }

        #region Implementations

        public T Add(T entity)
        {
            #region instrumentation

            _log.DebugFormat("entrando Add: entity {0} ", entity);

            #endregion

            if (entity == null)
                throw new InvalidOperationException("Entidad nula en Add" + nameof(entity));

            T returnEntity = AddModify(entity);

            #region instrumentation

            _log.DebugFormat("saliendo Add: entity {0} ", entity);

            #endregion

            return returnEntity;
        }

        public T Edit(T entity)
        {
            T returnEntity = AddModify(entity);

            return returnEntity;
        }


        /// <summary>
        ///     Method that adds or modifies the entity
        /// </summary>
        /// <param name="entity">Entity that will be modified</param>
        /// <returns></returns>
        private T AddModify(T entity)
        {
            #region instrumentation

            _log.DebugFormat("entrando AddModify: entity {0} ", entity);

            #endregion

            if (entity == null)
                throw new InvalidOperationException("Entidad nula en AddModify" + nameof(entity));

            try
            {
                _session.SaveOrUpdate(entity);
                _session.Flush();
            }
            catch (PropertyValueException e)
            {
                #region Instrumentation

                _log.ErrorFormat("Valor inválido en BaseDao AddModify tipo {0}, mensaje {1}, causa {2}", e, e.Message, e.TargetSite);

                #endregion
                throw;
            }
            catch (Exception e)
            {
                #region Instrumentation

                _log.ErrorFormat("Exception en BaseDao AddModify tipo {0}, mensaje {1}, causa {2}", e, e.Message, e.TargetSite);

                #endregion

                throw;
            }

            #region instrumentation

            _log.DebugFormat("saliendo Add: entity {0} ", entity.ToString());

            #endregion

            return entity;
        }

        /// <summary>
        ///     Method that returns all the records from an entity ordered by Id
        /// </summary>
        /// <returns></returns>
        public IList<T> GetAll()
        {
            IList<T> entityList;

            #region instrumentation

            _log.DebugFormat("entrando GetAll");

            #endregion

            try
            {
                entityList = _session.CreateCriteria(typeof(T)).AddOrder(Order.Asc("Id")).List<T>();
            }
            catch (SqlException e)
            {
                #region Instrumentation

                _log.ErrorFormat("Error en sql en BaseDao GetAll tipo {0}, mensaje {1}, causa {2}", e, e.Message, e.TargetSite);

                #endregion

                throw;
            }
            catch (Exception e)
            {
                #region Instrumentation

                _log.ErrorFormat("Exception en BaseDao GetAll tipo {0}, mensaje {1}, causa {2}", e, e.Message, e.TargetSite);

                #endregion

                throw;
            }

            #region instrumentation

            _log.DebugFormat("saliendo GetAll: list {0} size {1}", entityList, entityList.Count);

            #endregion


            return entityList;
        }

        /// <summary>
        ///     Method that returns an object from its id
        /// </summary>
        /// <param name="id">id of the object</param>
        /// <returns>Object</returns>
        public T GetById(long id)
        {
            T entity;

            #region instrumentation

            _log.DebugFormat("entrando GetById: id {0}", id);

            #endregion

            try
            {
                ICriteria criteria = _session.CreateCriteria(typeof(T));
                criteria.Add(Restrictions.Eq("Id", id));

                entity = (T)criteria.AddOrder(Order.Asc("Id")).UniqueResult();
            }
            catch (HibernateException e)
            {
                #region Instrumentation

                _log.ErrorFormat("Error en Nhibernate en BaseDao GetById tipo {0}, mensaje {1}, causa {2}", e, e.Message, e.TargetSite);

                #endregion

                throw;
            }
            catch (SqlException e)
            {
                #region Instrumentation

                _log.ErrorFormat("Error en sql en BaseDao GetById tipo {0}, mensaje {1}, causa {2}", e, e.Message, e.TargetSite);

                #endregion

                throw;
            }
            catch (Exception e)
            {
                #region Instrumentation

                _log.ErrorFormat("Error en BaseDao GetById tipo {0}, mensaje {1}, causa {2}", e, e.Message, e.TargetSite);

                #endregion

                throw;
            }

            #region instrumentation

            _log.DebugFormat("saliendo GetById: entity {0}", entity);

            #endregion

            return entity;
        }

        /// <summary>
        ///     Method that deletes the object from the persistance if exists
        /// </summary>
        /// <param name="entity">Object that is going to be deleted</param>
        /// <returns></returns>
        public T Delete(T entity)
        {
            #region instrumentation

            _log.DebugFormat("entrando Delete: entity {0}", entity);

            #endregion

            if (entity == null)
                throw new InvalidOperationException("Entidad nula en Delete" + nameof(entity));

            try
            {
                _session.Delete(entity);
            }
            catch (SqlException e)
            {
                #region Instrumentation

                _log.ErrorFormat("Error en sql en BaseDao Delete tipo {0}, mensaje {1}, causa {2}", e, e.Message, e.TargetSite);

                #endregion

                throw;
            }
            catch (Exception e)
            {
                #region Instrumentation

                _log.ErrorFormat("Error en BaseDao Delete tipo {0}, mensaje {1}, causa {2}", e, e.Message, e.TargetSite);

                #endregion

                throw;
            }

            #region instrumentation

            _log.DebugFormat("saliendo Delete: entity {0}", entity);

            #endregion

            return entity;
        }

        #endregion
    }
}