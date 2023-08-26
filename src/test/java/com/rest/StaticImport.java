package com.rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class StaticImport {
	
	@Test
	public void test() {
		given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
		when().
		       get("/workspaces").
		then().
			   statusCode(200).
			   body("name", is(equalTo("Vishal Raymal")),
					   "email", is(equalTo("vishalraymal.gmail.com")));
		
	}

}
