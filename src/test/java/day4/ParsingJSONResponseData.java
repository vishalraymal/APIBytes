package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {
	
	@Test(priority=1, enabled=false)
	void testJSONResponse()
	{
		// Approach 1
		/* given()
			.contentType("ContentType.JSON")
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[3].title", equalTo("The Lord of the Rings")); */
		
		//Approach 2
	/*	Response res= given()
						.contentType("ContentType.JSON")
					.when()
						.get("http://localhost:3000/store");
		
		// validation 1
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		//validation 2
		String headerVal = res.header("Content-Type");
		Assert.assertEquals(headerVal, "application/json; charset=utf-8");
		//validation 3
		String titleVal = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(titleVal, "The Lord of the Rings");   */
	}
	
	@Test(priority=2, enabled=false)
	void testJSONResponseforTitle_demo1()
	{
		Response res= given()
				.contentType("ContentType.JSON")
			.when()
				.get("http://localhost:3000/store");
		
		int noOfBodyObjects = res.body().path("book.size()");
		
		boolean status=false;
		for(int i=0 ; i < noOfBodyObjects; i++)
		{
			String bookTitleValue= res.jsonPath().get("book["+i+"].title").toString();
			System.out.println(bookTitleValue);
			if(bookTitleValue.equals("The Lord of the Rings"))
			{
				Assert.assertTrue(true, "Title matched found");
				status=true;
			}
		}
		if(status==false)
		{
			Assert.assertTrue(false,"Title not matched found");
		}
		
	}
	
	@Test(priority=3, enabled=true)
	void testJSONResponseforTitle_demo2()
	{
		Response res= given()
				.contentType("ContentType.JSON")
			.when()
				.get("http://localhost:3000/store");
		
		// Search title of book - validation 1
		JSONObject jo = new JSONObject(res.asString());
		boolean status=false;
		for(int i=0 ; i < jo.getJSONArray("book").length(); i++)
		{
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(bookTitle.equals("The Lord of the Rings"))
			{
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	
	// find total price
		double total_price =0;
		for(int i=0; i < jo.getJSONArray("book").length(); i++)
		{
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			total_price = total_price+Double.parseDouble(price);
		}
		System.out.println("Total Price :"+ total_price);
		Assert.assertEquals(total_price,53.92);  
	
	}
	
}
