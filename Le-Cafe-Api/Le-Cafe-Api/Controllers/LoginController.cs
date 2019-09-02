using System.Reflection;
using System.Web.Http;
using log4net;

namespace Le_Cafe_Api.Controllers
{
    [RoutePrefix("api/login")]
    public class LoginController : ApiController
    {
        private static readonly ILog _log = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);

        [HttpGet]
        [Route("HelloWorld")]
        public IHttpActionResult HolaMundo()
        {
            return Ok(true);
        }
    }
}
