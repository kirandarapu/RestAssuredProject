package Day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import  static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequests {

	int id;
	@Test(priority=1)
	void getUser() {
		
		given() //content type
		
		.when() //type of request
		 .get("https://reqres.in/api/users?page=2")
		 
		 
		.then()//status code comparision
		 .statusCode(200)
		 .body("page",equalTo(2))
		 .log().all();//print the 
	} 
	
	@Test(priority=2)
	void createUser() {
		
		HashMap data=new HashMap();
		data.put("name", "Kiran");
		data.put("job", "Tester");
		
		id=given()
		 .contentType("application/json")
		 .body(data)
		
		.when()
		 .post("https://reqres.in/api/users")
		 .jsonPath().getInt("id");
		 
		//.then()
	//	.statusCode(201)
	//	.log().all();
	}
	
	@Test(priority=3,dependsOnMethods= {"createUser"})
	void updateUser() {
		HashMap hs=new HashMap();
		hs.put("name", "John");
		hs.put("job", "Devops Engineer");
		
		given()
		.contentType("application/json")
		.body(hs)
		
		.when()
		.put("https://reqres.in/api/users/"+id)
		
		
		.then()
		.statusCode(200)
		.log().all();
	}
	@Test(priority=4,dependsOnMethods= {"updateUser"})
	void deleteUser() {
		
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204)
		.log().all();
	}

}
