using System.Reflection;
using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using log4net;
using NHibernate;
using NHibernate.Tool.hbm2ddl;

namespace daoNHibernate.implementation
{
    public class FluentNHibernateHelper
    {
        private static readonly ILog _log = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);
        /// <summary>
        /// Method that generates the instance of Nhibernate for persistance with its respective configuration
        /// </summary>
        /// <returns></returns>
        public static ISessionFactory CreateFactorySession()
        {
            #region instrumentation
            _log.DebugFormat("creando fabrica de Fluent NHibernate ");
            #endregion

            ISessionFactory returnConfiguration = Fluently.Configure().Database(
                    MySQLConfiguration.Standard.ConnectionString(
                        x => x.FromConnectionStringWithKey("MySql")))
                .Mappings(m => m.FluentMappings.AddFromAssembly(Assembly.GetExecutingAssembly()))
                //.ExposeConfiguration(cfg => new SchemaUpdate(cfg).Execute(false, true))
                .BuildSessionFactory();

            #region instrumentation
            _log.DebugFormat("saliendo de creación de fabruca de Fluent NHibernate: Resultado {0} ", returnConfiguration);
            #endregion

            return returnConfiguration;
        }
    }
}