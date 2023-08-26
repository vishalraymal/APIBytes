package day7;


import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
	
	
	@Test(enabled=true, priority=1)
	void testBasicAuthentication()
	{
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true));	
	}
	
	@Test(enabled=true,priority=2)
	void testDigestAuthentication()
	{
		given()
		.auth().digest("postman", "password")
	.when()
		.get("https://postman-echo.com/basic-auth")
	
	.then()
		.statusCode(200)
		.body("authenticated", equalTo(true));	
	}

	@Test(enabled=true,priority=3)
	void testPreemptiveAuthentication()
	{
		given()
		.auth().preemptive().basic("postman", "password")
	.when()
		.get("https://postman-echo.com/basic-auth")
	
	.then()
		.statusCode(200)
		.body("authenticated", equalTo(true));	
	}

	
	@Test(enabled=true,priority=4)
	void testBearerAuthentication()
	{
		String bearer_token= "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";
		given()
			.headers("Authorization","Bearer "+bearer_token)
		.when()
			.get("https://api.github.com/user/repos")
	
		.then()
			.statusCode(200);
	}
	
	
	@Test(enabled=false, priority=5)
	void testOAuth1Authentication() {
		
		given()
			.auth().oauth("consumerKey","consumerSecret", "accessToken", "tokenSecret") // This is for OAuth1.0 Authentication
		.when()
			.get("url")
		.then()
			.log().all();
	
	}
	
	@Test(enabled=true, priority=6)
	void testOAuth2Authentication() {
		
		given()
			.auth().oauth2("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")
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.log().body();
	}
	
	@Test(enabled=true, priority=7)
	void testAPIKeyAuthentication() {
		given()
			.pathParam("pathPara", "data/2.5/forecast/daily")
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
		.when()
			.get("http://api.openweathermap.org/{pathPara}")
		.then()
			.log().all();
		
		
	}
	
	
}
