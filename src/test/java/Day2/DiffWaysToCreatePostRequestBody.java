package Day2;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import  static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

//Different ways to create post request body
//post request body creation by  using HashMap
//post request body creation by  using org.json
//post request body creation by using pojo class
//post request body using json file data



public class DiffWaysToCreatePostRequestBody {

	
	int id;
	//@Test(priority=1)
	void testPostusingHashMap() {
		HashMap data=new HashMap();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[]= {"C","C++"};
		data.put("cources", courseArr);
		
		id=given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		.jsonPath().getInt("id");
//		.then()
//		.statusCode(201)
//		.body("name", equalTo("Scott"))
//		.body("location",equalTo( "France"))
//		.body("phone",equalTo("123456"))
//		.body("cources[0]", equalTo("C"))
//		.body("cources[1]", equalTo("C++"))
//		.header("Content-Type","application/json; charset=utf-8")
//		.log().all();
		
	}
	
	
	//@Test(priority=1)
	void testPostUsingJsonLibrary() {
		
		JSONObject data1=new JSONObject();
		
		data1.put("name", "Sam");
		data1.put("location", "India");
		data1.put("phone", "9876543210");
		
		String CourceArr[]= {"Python","Devops"};
		data1.put("cources", CourceArr);
		
		id=given()
		.contentType("application/json")
		.body(data1.toString())
		
		.when()
		.post("http://localhost:3000/students")
		.jsonPath().getInt("id");
		
//		.then()
//		.statusCode(201)
//		.log().all();
//		
	}
	//@Test(priority=1)
	void testPostusingPojo() {
		
		Pojo_PostRequest data2=new Pojo_PostRequest();
		data2.setName("Scott");
		data2.setLocation("France");
		data2.setPhone("123456");
		
		String courcesArr[]= {"C","C++"};
		data2.setCources(courcesArr);
		
		
		id=given()
		.contentType("application/json")
		.body(data2)
		
		.when()
		.post("http://localhost:3000/students")
		.jsonPath().getInt("id");
//		.then()
//		.statusCode(201)
//		.log().all();
		
	}
	
	@Test(priority=1)
	void testJsonExternalFile() throws FileNotFoundException {
		
		File f=new File(".\\body.json");
		
		FileReader fr= new FileReader(f);
		
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);
		
		id=given()
				.contentType("application/json")
				.body(data)
				
				.when()
				.post("http://localhost:3000/students")
				.jsonPath().getInt("id");
		
		
		
		
		
	}
	
	
	@Test(priority=2)
	void deleteUser() {
		
		given()
		
		.when()
		.delete("http://localhost:3000/students/"+id)
		
		.then()
		.statusCode(200);
	}
	
	
	
	}
