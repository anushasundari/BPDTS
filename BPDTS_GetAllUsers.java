import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;

public class BPDTS_GetAllUsers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://bpdts-test-app-v2.herokuapp.com/";
		given().log().all().header("Content-Type","application/json").body("")
		.when().get("/users").then().log().all().assertThat().statusCode(200).header("Content-Length",equalTo("175719"));

	}

}
