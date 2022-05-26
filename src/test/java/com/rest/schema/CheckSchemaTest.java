package com.rest.schema;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

public class CheckSchemaTest {

    @Test
    public void booking_Schema_Test() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given()
                .contentType(ContentType.JSON)
                .body(new File ("/Users/dzmitryrazhkou/Documents/Automation/RestAssuredSession/" +
                        "src/test/resources/booking_body.json"))
                .when().log().all()
                .post("/booking")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body(matchesJsonSchemaInClasspath("booking_schema.json"));
    }

    @Test
    public void getUserAPI_Schema_Test() {

        String name = "Gauranga Ganaka";
        String gender = "male";
        String status = "inactive";

        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .contentType(ContentType.JSON)
                .param("name", name)
                .param("gender", gender)
                .param("status", status)
                .when().log().all()
                .get("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body(matchesJsonSchemaInClasspath("getUser.json"));


    }
}
