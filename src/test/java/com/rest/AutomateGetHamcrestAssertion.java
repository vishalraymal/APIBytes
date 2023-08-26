package com.rest;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AutomateGetHamcrestAssertion {
	
	@Test
	public void test() {
		Response res = given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
		when().
		       get("/workspaces").
		then().
			   //log().all().
			   assertThat().
			   statusCode(200).
			   extract().
			   response();
		
		System.out.println("Response ==> "+ res.asString());
			  
		
	}

}
