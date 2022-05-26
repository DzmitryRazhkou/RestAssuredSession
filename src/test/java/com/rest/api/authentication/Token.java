package com.rest.api.authentication;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class Token {

    @Test
    public static Map<Object, Object> getAccessToken() {

        Map<String, String> formParams = new HashMap<>();

        formParams.put("refresh_token", "f8d252f496102cd12e2819be60eebfb212b03d62");
        formParams.put("client_id", "f0a54b3505bed2f");
        formParams.put("client_secret", "d9271586c973304b361f9ab7b8988cd598220569");
        formParams.put("grant_type", "refresh_token");

        JsonPath tokenJson = given()
                .formParams(formParams)
                .when()
                .post("https://api.imgur.com/oauth2/token")
                .then()
                .extract()
                .jsonPath();

        System.out.println(tokenJson.getMap(""));
        return tokenJson.getMap("");

    }
}
