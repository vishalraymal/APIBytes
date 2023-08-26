package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// POJO --- Serilize --> JSON object --- Deseilize --- POJO

public class SerilizationDeserilization {
	
	
	@Test(priority =1)
	void convertPOJOtoJson() throws JsonProcessingException
	{
		Students stuPOJO = new Students();
		stuPOJO.setName("Vishal");
		stuPOJO.setPhone("7208751589");
		stuPOJO.setLocation("Nagpur");
		String courses[] = {"C","Java"};
		stuPOJO.setCourses(courses);
		
		//Conver Java object into Json object( Serilization)
		ObjectMapper objMapper = new ObjectMapper();
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stuPOJO);
		System.out.println(jsonData);
		
	}

	@Test(priority=2)
	void convertJsonToPOJO() throws JsonProcessingException
	{
		String jsonData="{\r\n"
				+ "  \"name\" : \"Vishal\",\r\n"
				+ "  \"location\" : \"Nagpur\",\r\n"
				+ "  \"phone\" : \"7208751589\",\r\n"
				+ "  \"courses\" : [ \"C\", \"Java\" ]\r\n"
				+ "}";
		
		//Conver json object into java Object
		ObjectMapper objMapper = new ObjectMapper();
		Students stuObj = objMapper.readValue(jsonData, Students.class);
		
		// Deserilization
		System.out.println("name : " + stuObj.getName());
		System.out.println("location : " + stuObj.getlocation());
		System.out.println("phone : " + stuObj.getPhone());
		System.out.println("course1 : " + stuObj.getCourses()[0]);
		System.out.println("course2 : " + stuObj.getCourses()[1]);

		
	}

}
