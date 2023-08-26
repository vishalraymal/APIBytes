package com.rest;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AutomateGetExtractSigleValueFromResponse {
	
	@Test
	public void test() {
		Response res= given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
		when().
		       get("/workspaces").
		then().
			   log().all().
			   assertThat().
			   statusCode(200).
			   extract().
			   response();
		
		//option 1
		//JsonPath jsonPath = new JsonPath(res.asString());
		//System.out.println("Workspace name : "+ jsonPath.getString("workspaces[0].name"));
		
		//option 2
		String workspaceName= JsonPath.from(res.asString()).getString("workspaces[1].name");
		System.out.println("Workspace name : "+ workspaceName);
		
		
		
		
		
		
	}

}
