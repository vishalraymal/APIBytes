package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class CreatUser {
	
	@Test
	void test_createUser(ITestContext context) {
		
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name());
		data.put("email", faker.internet().emailAddress());
		data.put("gender", "male");
		data.put("status", "inactive");
		
		String bearer_token="d732d243a255eecbb157935883b173e6818d6ec9b703d3578b0752436b0e764d";
		
		 Response res = given()
			.header("Authorization","Bearer "+bearer_token)
			.contentType("application/json")
			.body(data.toString())
			
						
		.when()
			.post("https://gorest.co.in/public/v2/users");
		
		JsonPath jp = new JsonPath(res.asString());
		int id = jp.getInt("id");
		System.out.println("Generated id : "+id);
		//context.setAttribute("user_id", id);
		context.getSuite().setAttribute("user_id", id);
		
		
		
	}

}
