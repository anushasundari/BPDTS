import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

//import org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;


public class BPDTS_Instructions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://bpdts-test-app-v2.herokuapp.com/";
		String response=given().log().all().header("Content-Type","application/json").body("")
		.when().get("/instructions").then().log().all().assertThat().statusCode(200).body("todo", equalTo("Create a short automated test for this API. Check that the data returned by the API is valid, and that ensure that each valid operation can be successfully called for each endpoint. Once you've built the tests, push the answer to Github or Gitlab, and send us a link. "))
		.header("Content-Length",equalTo("281")).extract().response().asString();
		
		//System.out.println(response);
		JsonPath js=new JsonPath(response); //for parsing Json
		String InstructionMessage=js.getString("todo");
		
		System.out.println(InstructionMessage);
		
	}

}
