package com.rest.api.authentication;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthAPIs {


//    Basic auth:
//    username/password

    @Test
    public void basic_Auth_aAPI_Test() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .when().log().all()
                .get("https://the-internet.herokuapp.com/basic_auth")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void OAuth2_API_Test() {

        given().log().all()
                .auth()
                .oauth2("8981efe61b42306790202f39383a5bfbdf4e6ef6")
                .when().log().all()
                .post("http://coop.apps.symfonycasts.com/api/3172/chickens-feed")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("action", equalTo("chickens-feed"));
    }

    @Test
    public void QAuth_API_WithTwoQueryParams_API_Test() {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer 427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c")
                .queryParam("name", "Dev Agarwal")
                .queryParam("gender", "female")
                .when().log().all()
                .get("/public/v2/users")
                .then()
                .statusCode(200)
                .and()
                .header("Server", "nginx")
                .and()
                .header("Content-Type", "application/json; charset=utf-8");
    }

    @Test
    public void checkOAuth2_APITest() {
        RequestSpecification request =
                RestAssured
                        .given().log().all()
                        .auth()
                        .oauth2("8981efe61b42306790202f39383a5bfbdf4e6ef6");
        Response response = request.post("http://coop.apps.symfonycasts.com/api/3172/chickens-feed");

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void getAuth2TokenAPITest() {
        RequestSpecification request =
                RestAssured
                        .given()
                        .formParam("client_id", "GansAPIapp")
                        .formParam("client_secret", "bcd08bcf0d42c7d7490596d79e76f32f")
                        .formParam("grant_type", "client_credentials");
        Response response = request.post("http://coop.apps.symfonycasts.com/token");
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());

        String tokenId = response.jsonPath().get("access_token");
        System.out.println("API Token id is: " +tokenId);

//        feed chickens api:

//        RequestSpecification request1 =
//                RestAssured
//                        .given()
//                        .auth()
//                        .oauth2(tokenId);
//        Response response1 = request1.post("http://coop.apps.symfonycasts.com/token");
//        System.out.println(response.getStatusCode());
//        System.out.println(response.prettyPrint());
    }

    @Test
    public void TwitterStatusAPI_QAuth1_Test(){
        RequestSpecification request =
                RestAssured.given()
                        .auth()
                        .oauth("6zTuoeu3MaDPRQK2mv4biQP1l",
                                "mcEGcYEUUV0D97DqNCkcmUOjx9y4iuydGySVcuSdwLINtH3s38",
                                "1520535713116135429-XqerDX7eBhvxRlRnYUNvn3iHrcuZcZ",
                                "3sRsQiluUCIjxOi5U4y4aoysZMkx9XdoTMzlaM46MdwW0");
        Response response = request.post("https://api.twitter.com/1.1/statuses/update.json?status=This tweet fromRest Assured");

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }
}
