package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class HTTPRequest {
	
	int id;
	
	@Test(enabled=false)
	void listUsers()
	{
		
		//given()
		when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}

	
	@Test(enabled=true, priority=1)
	void createUser()
	{
		HashMap data = new HashMap();
		data.put("name", "Steve");
		data.put("job", "Cricketer");
		
		id = given()
			.contentType("application/json")
			.body(data)
	
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
	
	/*	.then()
			.statusCode(201)
			.log().all();   */
		
	}
	
	@Test(enabled=true, priority=2, dependsOnMethods= {"createUser"})
	void updateUser()
	{
		HashMap data = new HashMap();
		data.put("name", "Roger");
		data.put("job", "TennisPlayer");
		
		 given()
			.contentType("application/json")
			.body(data)
	
		.when()
			.put("https://reqres.in/api/users/"+id)
	
		.then()
			.statusCode(200)
			.log().all();   
		
		
	}
	
	
	@Test(priority=3, dependsOnMethods= {"updateUser"})
	void deleteUser()
	{
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
		
	}
	
	
	
	
	
	
	
}
