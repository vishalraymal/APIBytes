package day6;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class XMLSchemaValidator {
	
	@Test
	void validateXMLSchema()
	{
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
			.statusCode(200)
			.body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));
	}

}
