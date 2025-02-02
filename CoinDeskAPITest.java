import io.restassured.RestAssured;

import io.restassured.response.Response;

import org.json.JSONObject;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;



public class CoinDeskAPITest {



    @Test

    public void testCoinDeskAPI() {

       
        Response response = given()

                .when()

                .get("https://api.coindesk.com/v1/bpi/currentprice.json")

                .then()

                .statusCode(200) 

                .extract().response();



  
        JSONObject jsonResponse = new JSONObject(response.asString());

        JSONObject bpi = jsonResponse.getJSONObject("bpi");



        assert bpi.has("USD") : "Missing USD in BPI";

        assert bpi.has("GBP") : "Missing GBP in BPI";

        assert bpi.has("EUR") : "Missing EUR in BPI";

        String gbpDescription = bpi.getJSONObject("GBP").getString("description");

        assert gbpDescription.equals("British Pound Sterling") : "GBP description mismatch";


        System.out.println("Test Passed: API response is valid.");

    }

}