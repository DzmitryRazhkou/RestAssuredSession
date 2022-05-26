package com.rest.api.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GET_BDD_API {

//    Rest Assured BDD:

    /**
     * given()
     * when()
     * then()
     * and()
     */

    @Test
    public void getAPICircuitTest_1() {
        given().log().all()
                .when().log().all()
                .get("http://ergast.com/api/f1/2015/circuits.json")
                .then().log().all()
                .assertThat()
                .body("MRData.CircuitTable.Circuits.circuitId", hasSize(19));
    }

    @Test
    public void getAPICircuitTest_statusCode() {

        Response response =
                given().log().all()
                        .when().log().all()
                        .get("http://ergast.com/api/f1/2015/circuits.json");

        int statusCode = response.getStatusCode();
        System.out.println("API Response Status Code: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        System.out.println(response.prettyPrint());
    }

    @Test
    public void getAPICircuitTest_contentLength() {

        RestAssured.baseURI = "http://ergast.com";
        given().log().all()
                .when().log().all()
                .get("/api/f1/2015/circuits.json")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("Content-Length", equalTo("4351"));
    }

    @Test
    public void getAPIVerify_Md5() {

        String paramValue = "example_text";
        String expectedMd5Value = "fa4c6baa0812e5b5c80ed8885e55a8a6";

        given().log().all()
                .param("text", paramValue)
                .when().log().all()
                .get("http://md5.jsontest.com")
                .then().log().all()
                .assertThat()
                .body("md5", equalTo(expectedMd5Value));
    }

    @Test
    public void getUser_response_Xml() {
        RestAssured.baseURI = "https://gorest.co.in";
        Response response = given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer 427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c")
                .header("Accept", "application/xml")

                .when().log().all()
                .get("/public/v2/users?name=Dhruv Achari");

        System.out.println(response.statusCode());
        System.out.println(response.prettyPrint());

//        XmlPath xmlPath = response.xmlPath();
//        String success = xmlPath.getString("name");
//        System.out.println("Value: " +success);


    }
}
