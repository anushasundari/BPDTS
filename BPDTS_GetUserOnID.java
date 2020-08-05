import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BPDTS_GetUserOnID {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://bpdts-test-app-v2.herokuapp.com/";
		String test_id=given().log().all().header("Content-Type","application/json").body("")
		.when().get("/users").then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js= new JsonPath(test_id);
		int UserID=js.getInt("id[4]");
		String Exp_FirstName="Rosita";
		String Exp_LastName="Ferrulli";
		String Exp_City="Shuishi";
		System.out.println("id from path is " +UserID);
		
		
		given().log().all().header("Content-Type","application/json").body("")
		.when().get("/user/{UserID}",UserID).then().log().all().assertThat().statusCode(200).body("first_name",equalTo(Exp_FirstName)).body("last_name",equalTo(Exp_LastName)).body("city",equalTo(Exp_City)).extract().response().asString();
		/*JsonPath js2=new JsonPath(response); //for parsing Json
		
		String Act_FirstName=js2.getString("first_name");
		String Act_LastName=js2.getString("last_name");
		String Act_City=js2.getString("city"); */
		
	}

}
