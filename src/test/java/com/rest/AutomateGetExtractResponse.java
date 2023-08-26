package com.rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AutomateGetExtractResponse {
	
	@Test
	public void test() {
		given().
				baseUri("https://api.postman.com").
				header("X-Api-Key", "PMAK-632593eba29a4913b91d760f-ed04eb616f30ebc57f3fe08e328d8b0cd5").
		when().
		       get("/workspaces").
		then().
			   log().all().
			   assertThat().
			   statusCode(200).
			   body("workspaces.name", hasItems("My Workspace", "Automation Testing v1.1"),
					   "workspaces.type", hasItems("personal", "personal"),
					   "workspaces[0].name", equalTo("My Workspace"),
					   "workspaces[0].name", is(equalTo("My Workspace")),
					   "workspaces.size()", equalTo(3),
					   "workspaces.name", hasItem("My Workspace"),
					   "workspaces.name",contains("My Workspace", "Automation Testing v1.1", "My Workspace 2"),
					   "workspaces.name", containsInAnyOrder("My Workspace 2", "Automation Testing v1.1", "My Workspace"),
					   "workspaces.name", is(not(empty())),
					   "workspaces.name", is(not(emptyArray())),
					   "workspaces.name", hasSize(3),
					   "workspaces[0]", hasKey("id"),
					   "workspaces[0]", hasValue("personal"),
					   "workspaces[0]", hasEntry("id","720d44bd-dd8a-4ddf-86a5-91695512d67e"),
					   "workspaces[0].name", allOf(startsWith("My"), containsString("Workspace"))
					   );
		
	}

}
