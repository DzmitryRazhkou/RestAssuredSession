package com.rest.api.get;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResponseSpecBuilderTest {

    /**
     * t1, t2, t3, t4 ...... t100
     * status code = 200 -> 201
     * content-type
     * header
     */

        ResponseSpecBuilder res = new ResponseSpecBuilder();
        ResponseSpecification resSpec_200_OK = res
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectHeader("Server", "nginx")
                .build();

    ResponseSpecification resSpec_400_BAD_REQUEST = res
            .expectContentType(ContentType.JSON)
            .expectStatusCode(400)
            .expectHeader("Server", "nginx")
            .build();

    ResponseSpecification resSpec_401_AUTH_FAIL = res
            .expectContentType(ContentType.JSON)
            .expectStatusCode(401)
            .expectHeader("Server", "nginx")
            .build();

    @Test
    public void ResponseSpecTest() {
        RestAssured.baseURI = "https://gorest.co.in";
        given()
                .header("Authorization", "Bearer 427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c")
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat()
                .spec(resSpec_200_OK);
    }

    @Test
    public void ResponseSpec_Auth_Fail_Test() {
        RestAssured.baseURI = "https://gorest.co.in";
        given()
                .header("Authorization", "Bearer 427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb11111111")
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat()
                .spec(resSpec_401_AUTH_FAIL);
    }
}
