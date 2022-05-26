package com.rest.api.put;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PUT_BDD_API {

//    Create a user with post call
//    User info
//    Update the info with put call
//    1. Other attributes should remain same
//    2. The attribute which has been changed, need tto check

    @Test
    public void update_User_PUT_API_Test() {

//        1. Create a POST Request with Payload
        User us = new User("Tim Armstrong", "male", "tim.armstrong@190.com", "active");

//        Convert this POJO to JSON -- using Jackson API - ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        String userJson = null;

        try {
            userJson = mapper.writeValueAsString(us);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("POST Call JSON is: " +userJson);

//        Write a post call:

        RestAssured.baseURI = "https://gorest.co.in";

        String userID = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c")
                .body(userJson)
                .when()
                .post("public/v2/users")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .extract().path("id");

        System.out.println("User ID is: " +userID);

//        Call the PUT API:

        us.setEmail("timmmy.armstrong!!!@150.com");

//        Convert this POJO to JSON -- using JACKSON API

        String updatedUserJson = null;

        try {
            updatedUserJson = mapper.writeValueAsString(us);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c")
                .body(updatedUserJson)
                .when().log().all()
                .put("/public/v2/users/" +userID)
                .then().log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .body("email", equalTo(us.getEmail()))
                .and()
                .body("name", equalTo(us.getName()))
                .and()
                .body("status", equalTo(us.getStatus()));
    }

}
