package day8;


import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUser {

	@Test
	void test_getUser(ITestContext context) {
		
		int id = (Integer) (Integer) context.getSuite().getAttribute("user_id");  // this should come from createUser request
		String bearer_token="d732d243a255eecbb157935883b173e6818d6ec9b703d3578b0752436b0e764d";
		
		given()
			.header("Authorization", "Bearer "+bearer_token)
			.pathParam("id", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()	
			.statusCode(200)
			.log().all();
		
		
		
	}
	
	
}
