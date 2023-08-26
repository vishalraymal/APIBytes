package com.rest;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


public class AutomateGet_HamcrestAssertOnExtractedResponse {
	
	
	@Test
	public void hamcrest_assert_on_extracted_response()
	{
		
		String name= given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
		when().
		       get("/workspaces").
		then().
			   log().all().
			   assertThat().
			   statusCode(200).
			   extract().
			   response().path("workspaces[1].name");
		
		//Hamcrest Assertion
		assertThat(name, equalTo("Automation Testing v1.1"));
		
		//TestNg Assertion
		Assert.assertEquals(name, "Automation Testing v1.1");
		
		
	}

	
	

}
