package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	@Test
	void testDeleteUser(ITestContext context) {
		int id = (Integer) context.getSuite().getAttribute("user_id");  // this should come from createUser request
		String bearer_token="d732d243a255eecbb157935883b173e6818d6ec9b703d3578b0752436b0e764d";
		
		given()
			.header("Authorization", "Bearer "+bearer_token)
			.pathParam("id", id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()	
			.statusCode(204)
			.log().all();
		
	}

}
