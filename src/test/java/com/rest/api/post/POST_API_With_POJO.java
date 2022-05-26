package com.rest.api.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class POST_API_With_POJO {

//    Create a user
//    Post - URL
//    Request JSON BODY;
//    USER JAVA CLASS (POJO) ---> JSON OBJECT
//    Encapsulation ---> private variables ---> public (getters ana setters methods)
//    POJO --- Plain Old Java Object ---  Java Class ---> private variables ---> public (getters ana setters)

    @Test
    public void createUser_With_Pojo_Test() {
        User us = new User("Matthew Freeman", "male", "matt.freeman@1000.com", "inactive");

//        Convert POJO to JSON --- Serialization --- JACKSON API:

        ObjectMapper mapper = new ObjectMapper();
        String userJson = null;
        try {
            userJson = mapper.writeValueAsString(us);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(userJson);

        RestAssured.baseURI = "https://gorest.co.in";

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c")
                .body(userJson)
                .when().log().all()
                .post("/public/v2/users")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .body("name", equalTo(us.getName()))
                .body("email", equalTo(us.getEmail()))
                .body("status", equalTo(us.getStatus()));
    }
}
