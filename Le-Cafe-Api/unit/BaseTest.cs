using daoNHibernate.implementation;
using libraries;
using NHibernate;
using NUnit.Framework;

namespace Unit
{
    public abstract class BaseTest
    {
        internal static ISessionFactory SessionFactory = FluentNHibernateHelper.CreateFactorySession();
        public ISession Session { get; set; }

        [SetUp]
        public void Init()
        {
            GlobalRegistry.GetInstance().Session = SessionFactory.OpenSession();
        }

        [TearDown]
        public void CleanUp()
        {
            if (GlobalRegistry.GetInstance().Session != null &&
                GlobalRegistry.GetInstance().Session.IsOpen)
            {
                GlobalRegistry.GetInstance().Session.Dispose();
            }
        }
    }
}