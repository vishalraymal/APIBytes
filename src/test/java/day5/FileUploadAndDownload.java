package day5;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadAndDownload {
	
	@Test(enabled=false)
	void singleFileUpload()
	{
		File myfile = new File("C:\\Users\\ThisPc\\Desktop\\test1_upload.txt");
		given()
			.multiPart("file",myfile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8888/uploadFile")
		
		.then()	
			.statusCode(200)
			.body("fileName", equalTo("test1_upload.txt"))
			.log().body();
	}
	
	@Test(enabled=true, priority=1)
	void multipleFileUpload()
	{
		File myfile1 = new File("C:\\Users\\ThisPc\\Desktop\\test1_upload.txt");
		File myfile2 = new File("C:\\Users\\ThisPc\\Desktop\\test2_upload.txt");
		
		File filearr[]= {myfile1, myfile2};  //won't work for all apis
		given()
			.multiPart("files",myfile1)
			.multiPart("files",myfile2)
		//	.multiPart("files",filearr)
		 	.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8888/uploadMultipleFiles")
		
		.then()	
			.statusCode(200)
			.body("[0].fileName", equalTo("test1_upload.txt"))
			.body("[1].fileName", equalTo("test2_upload.txt"))
			.log().body();
		
	}
	
	@Test(priority=2)
	void fileDownload()
	{
		given()
		
		.when()
			.get("http://localhost:8888/downloadFile/test1_upload.txt")
			
		.then()
			.statusCode(200)
			.log().body();
			
	}

}
