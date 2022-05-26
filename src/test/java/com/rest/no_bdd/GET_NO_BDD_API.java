package com.rest.no_bdd;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class GET_NO_BDD_API {

    @Test
    public void getUser_Non_BDD_Test() {
        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c");

        Response response = request.get("/public/v2/users");

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
        System.out.println(response.getHeader("Server"));
    }



    @Test
    public void getUser_Non_BDD_WithQuery_Test() {
        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c");

        request.queryParam("name", "Lars Frederiksen");
        request.queryParam("status", "active");

        Response response = request.get("/public/v2/users");

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
        System.out.println(response.getHeader("Server"));
    }


    @Test
    public void getUser_Non_BDD_HashMap_Test() {
        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "427b19d3989ddf5b59efd44c95695df96e01627292bc10c16936eaeb9551fe1c");

        HashMap<String, String> params = new HashMap<>();
        params.put("name", "Lars Frederiksen");
        params.put("status", "active");

        request.queryParams(params);

        Response response = request.get("/public/v2/users");

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
        System.out.println(response.getHeader("Server"));
        System.out.println(response.getStatusLine());
        System.out.println(response.getContentType());
        System.out.println(response.getHeader("Server"));

        JsonPath js = response.jsonPath();
        System.out.println(js.getString("name"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getHeader("Server"), "nginx");
    }


}
