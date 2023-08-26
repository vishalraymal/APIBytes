package day3;


import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
	
	@Test(priority=1,enabled=false)
	void testCookies()
	{
		given()
		
		.when()
			.get("https://www.google.com")
		.then()
			.statusCode(200)
			.cookie("AEC","AakniGNsYGWsaUTqM2P8hNoFpPE44cvf8Rm4WqvHMXhftCE3kbqE793i3kk") // Every time cookie will change
			.log().all();
	}

	
	@Test(priority=2)
	void getCookieInfo()
	{
         Response res = given()
		
		.when()
			.get("https://www.google.com");
         
         
		//get single cookie info
         
         String cookie_var = res.getCookie("AEC");
         System.out.println("Value of cookie:"+ cookie_var);
         
         //get multiple cookies
         Map<String, String> cookies_vals= res.getCookies();
         for(String k: cookies_vals.keySet())
         {
        	 String cookie_name = res.getCookie(k);
        	 System.out.println(k+"   "+cookie_name);
        	 
         }
	}
}
