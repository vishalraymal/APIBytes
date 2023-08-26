package day3;


import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HeaderDemo {

	
	@Test(priority=1,enabled=false)
	void testHeaders()
	{
		given()
		
		.when()
			.get("https://www.google.com")
		.then()
			.statusCode(200)
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws");
	}
	
	@Test(priority=2,enabled=true)
	void getHeadersInfo()
	{
		Response res= given()
		
		.when()
			.get("https://www.google.com");
		
		//Get single Header value
		String headerValue = res.header("Content-Type");
		System.out.println("Content-Type header info: "+headerValue);
		System.out.println("-------- All Headers info-----------");
		//Get All Header
	    Headers headerValues= res.headers();
	    for(Header hd : headerValues)
	    {
	    	System.out.println(hd.getName()+"        "+hd.getValue());
	    }
	    
	    
	    
	    
	    
		
	}
}
