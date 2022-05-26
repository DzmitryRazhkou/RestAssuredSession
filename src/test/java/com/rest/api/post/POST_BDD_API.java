package com.rest.api.post;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class POST_BDD_API {

    @Test
    public void tokenPostAPI_Test() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given().log().all()
                .contentType(ContentType.JSON)
                .body("{\"username\" : \"admin\", \"password\" : \"password123\"}")
                .when()
                .post("/auth")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void tokenPostAPI_File_Test() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String tokenId = given().log().all()
                .contentType(ContentType.JSON)
                .body(new File("/Users/dzmitryrazhkou/Documents/Automation/RestAssuredSession/src/test/java/data/credentials.json"))
                .when()
                .post("/auth")
                .then().log().all()
                .extract().path("token");
        System.out.println(tokenId);
        Assert.assertNotNull(tokenId);
    }

    @Test
    public void createUser_Post_API_JSONString() {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c")
                .body("{\"name\":\"Jessica Smith\", \"gender\":\"female\", \"email\":\"jessica.smith@15ce.com\", \"status\":\"inactive\"}")
                .when().log().all()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .body("status", equalTo("inactive"));
    }


    @Test
    public void createUser_Post_API_File_JSONString() {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c")
                .body(new File("src/test/java/data/userData.json"))
                .when().log().all()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .body("status", equalTo("active"))
                .body("email", equalTo("edward.evans@15ce.com"));
    }
}
