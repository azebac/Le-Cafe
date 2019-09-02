using System.Runtime.CompilerServices;
using NHibernate;

namespace libraries
{
    public sealed class GlobalRegistry
    {
        #region Properties

        private static GlobalRegistry _instance;   // Stores the singleton of the instance of the class global registry
        public ISession Session { get; set; }      //Saved sessions

        #endregion

        #region Constructors

        private GlobalRegistry()
        {
        }

        #endregion

        #region Functions/Procedures

        /// <summary>
        /// Gets the instance of the registry
        /// </summary>
        /// <returns>Instance of the registry</returns>
        public static GlobalRegistry GetInstance()
        {
            if (_instance == null)
                CreateInstance();

            return _instance;
        }

        /// <summary>
        /// Creates safely the instance of the registry in multithreaded enviroments
        /// </summary>
        [MethodImpl(MethodImplOptions.Synchronized)]
        private static void CreateInstance()
        {
            if (_instance == null)
                _instance = new GlobalRegistry(); ;
        }

        #endregion
    }
}
