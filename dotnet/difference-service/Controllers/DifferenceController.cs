using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace WebApiTestV2.Controllers
{
	[Route("api/[controller]")]
	public class DifferenceController : Controller
	{
		[HttpGet]
		public JsonResult Get([FromQuery(Name = "first")] string first, [FromQuery(Name = "second")] string second)
		{
			string[] firstArray = first.Split(',');
			string[] secondArray = second.Split(',');
			var diff1 = firstArray.Except(secondArray);
			var diff2 = secondArray.Except(firstArray);
			var map = new Dictionary<string, IEnumerable<string>>();
			map.Add("REMOVED", diff1);
			map.Add("NEW", diff2);
			Console.WriteLine(map);
			return Json(map);
		}

		[HttpPost]
		public JsonResult Post([FromBody] Name name)
		{
			var first = name.first;
			var second = name.second;
			string[] firstArray = first.Split(',');
			string[] secondArray = second.Split(',');
			var diff1 = firstArray.Except(secondArray);
			var diff2 = secondArray.Except(firstArray);
			var map = new Dictionary<string, IEnumerable<string>>();
			map.Add("REMOVED", diff1);
			map.Add("NEW", diff2);
			Console.WriteLine(map);
			return Json(map);
		}
	}

	public class Name
	{
		public string first { get; set; }
		public string second { get; set; }
	}
}