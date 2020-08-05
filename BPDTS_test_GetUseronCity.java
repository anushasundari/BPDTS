import io.restassured.*;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import org.testng.Assert;
	public class BPDTS_test_GetUseronCity {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			//Validate Add place API
			int[]   UserID = new int[]{ 1,854 };
			String[] Exp_FirstName   = new String[20];
			String[] Act_FirstName   = new String[20];
			String[] Act_lastName   = new String[20];
			String[] Exp_lastName   = new String[20];
			String[] Exp_City   = new String[20];
			String city="Kax";
			RestAssured.baseURI="http://bpdts-test-app-v2.herokuapp.com/";
			for (int i = 0; i < UserID.length; i++) {
											
			String response=given().log().all().header("Content-Type","application/json").body("")
			.when().get("/user/{UserID}",UserID[i]).then().log().all().assertThat().statusCode(200).extract().response().asString();
			
			JsonPath js1=new JsonPath(response); //for parsing Json
			Exp_FirstName[i]=js1.getString("first_name");
			Exp_lastName[i]=js1.getString("last_name");
			Exp_City[i]=js1.getString("city");
			Assert.assertEquals("Kax", Exp_City[i]);
			System.out.println("The user- "+Exp_FirstName[i]+" lives in city "+Exp_City[i]);	
								
			}
			
			//Get users based on city name and verify them based on above user details
			
			String test_id=given().log().all().pathParam("city", city).header("Content-Type","application/json").body("")
				.when().get("/city/{city}/users").then().log().all().assertThat().statusCode(200).statusCode(200).extract().response().asString();		
			JsonPath js2= new JsonPath(test_id);
			for (int i = 0; i <= UserID.length; i++) {
					
					 Act_FirstName[i]=js2.getString("first_name["+i+"]");
					 Act_lastName[i]=js2.getString("last_name["+i+"]");
										
					Assert.assertEquals(Act_FirstName[i], Exp_FirstName[i]);
					Assert.assertEquals(Act_lastName[i], Exp_lastName[i]);
					
			}
					
		}


}
