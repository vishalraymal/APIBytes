package com.rest;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.config.LogConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.*;


public class AutomateGet_Demos {
	
	
	@Test(enabled=false)
	public void request_response_logging()
	{
		
		 given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
				log().all().
		when().
		       get("/workspaces").
		then().
			   log().all().
			   assertThat().
			   statusCode(200);
	}


	@Test(enabled=false)
	public void log_only_if_error()
	{
		
		 given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
				log().all().
		when().
		       get("/workspaces").
		then().
			   log().all().
			   assertThat().
			   statusCode(200);
	}
	
	@Test(enabled=false)
	public void log_only_if_validation_fails()
	{
		
		 given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
				config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
				//log().ifValidationFails().
		when().
		       get("/workspaces").
		then().
			 //  log().ifValidationFails().
			   assertThat().
			   statusCode(201);
	}
	
	@Test(enabled=true)
	public void blacklist_header()
	{
		Set<String> headers = new HashSet<String>();
		headers.add("X-Api-Key");
		headers.add("Accept");
		 given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
				//config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key"))).
				config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).	
				log().all().
		when().
		       get("/workspaces").
		then().
			   log().all().
			   assertThat().
			   statusCode(200);
	}

}
