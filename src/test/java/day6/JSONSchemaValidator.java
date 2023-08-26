package day6;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JSONSchemaValidator {
	
	@Test
	void validateJsonScehema()
	{
		given()
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"))
			.statusCode(200);
		
		
	}

}
