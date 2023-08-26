package day5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ParsingXMLResponse {
	
	@Test(enabled=false)
	void TestXMLResponse()
	{
		
		
		// Approach 1
	/*	given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
			.statusCode(200)
			.header("Content-Type","application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));  */
		
		//Approach 2
		Response res= given()
				
				.when()
					.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		
		String travellerFirstName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travellerFirstName, "Developer");
	
	}
	
	@Test
	void TestXMLResponseBody()
	{
			String res= given()
				
				.when()
					.get("http://restapi.adequateshop.com/api/Traveler?page=1").asString();

			XmlPath xmlObj = new XmlPath(res);
			List<String> travellers= xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
			List<String> travellers_name = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
			Assert.assertEquals(travellers.size(), 10);
			boolean status=false;
			String expectedTravellerName="karen";
			for(int i=0; i < travellers.size(); i++)
			{
				String travellerName = travellers_name.get(i);
				if(travellerName.equals(expectedTravellerName))
				{
					status=true;
					break;
				}
			}
			Assert.assertEquals(status, true, "Traveller name present in the response of traveller info");
		
	}

}
