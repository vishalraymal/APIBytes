package com.rest;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

import org.hamcrest.Matchers;

public class NonStaticImport {
	
	@Test
	public void test() {
		RestAssured.given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
		when().
		       get("/workspaces").
		then().
			   statusCode(200).
			   body("name", Matchers.is(Matchers.equalTo("Vishal Raymal")),
					   "email", Matchers.is(Matchers.equalTo("vishalraymal.gmail.com")));
		
	}

}
