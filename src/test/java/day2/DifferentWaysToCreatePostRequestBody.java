package day2;
import org.json.JSONObject;
import org.json.JSONTokener;
/*
1. HashMap
2. Using org.json
3. Using POJO(Plain Old Java Object)
4. Using external json file
 */
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


public class DifferentWaysToCreatePostRequestBody {
	
	
	//1. Using HashMap
	@Test(priority=1, enabled=false)
	void testPostUsingHashMap()
	{
		HashMap data = new HashMap();
		data.put("name", "Sachin");
		data.put("location", "india");
		data.put("phone", "0221234123");
		
		String[] courseArr = {"Batting", "Bowling"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Sachin"))
			.body("location", equalTo("india"))
			.body("phone",equalTo("0221234123"))
			.body("courses[0]",equalTo("Batting"))
			.body("courses[1]", equalTo("Bowling"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
	}
	
	
		//2. using org.json
		@Test(priority=2, enabled=false)
		void testPostUsingJsonLibrary()
		{
			JSONObject data = new JSONObject();
			data.put("name", "Stephen");
			data.put("location", "newzeland");
			data.put("phone", "022123999");
			String[] stringCor = {"Bowl","Fielding"};
			
			data.put("courses", stringCor);
			
			
			given()
				.contentType("application/json")
				.body(data.toString())
			
			.when()
				.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.body("name", equalTo("Stephen"))
				.body("location", equalTo("newzeland"))
				.body("phone",equalTo("022123999"))
				.body("courses[0]",equalTo("Bowl"))
				.body("courses[1]", equalTo("Fielding"))
				.header("Content-Type","application/json; charset=utf-8")
				.log().all();
		}
		
				//3. using POJO class
				@Test(priority=3, enabled=false)
				void testPostUsingPOJO()
				{
					POJO_PostRequest data = new POJO_PostRequest();
					// Set the data
					data.setName("Stephen");
					data.setLocation("newzeland");
					data.setPhone("022123999");
					String[] stringCor = {"Bowl","Fielding"};
					data.setCourses(stringCor);
					
					given()
						.contentType("application/json")
						.body(data)
					
					.when()
						.post("http://localhost:3000/students")
					
					.then()
						.statusCode(201)
						.body("name", equalTo("Stephen"))
						.body("location", equalTo("newzeland"))
						.body("phone",equalTo("022123999"))
						.body("courses[0]",equalTo("Bowl"))
						.body("courses[1]", equalTo("Fielding"))
						.header("Content-Type","application/json; charset=utf-8")
						.log().all();
				}
				
				
				//4. USING JSON FILE
				@Test(priority=4, enabled=true)
				void testPostUsingJsonFile() throws FileNotFoundException
				{
				
					File f = new File("./BODY.json");
					FileReader fr= new FileReader(f);
					JSONTokener jt = new JSONTokener(fr);
					JSONObject data = new JSONObject(jt);
					
					
					given()
						.contentType("application/json")
						.body(data.toString())
					
					.when()
						.post("http://localhost:3000/students")
					
					.then()
						.statusCode(201)
						.body("name", equalTo("Stephen"))
						.body("location", equalTo("newzeland"))
						.body("phone",equalTo("022123999"))
						.body("courses[0]",equalTo("Bowl"))
						.body("courses[1]", equalTo("Fielding"))
						.header("Content-Type","application/json; charset=utf-8")
						.log().all();
				}
	
	
	@Test(priority=100, enabled=true)
	void testDelete()
	{
		
		given()
		.when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(200);
	}
	
	
	

}
