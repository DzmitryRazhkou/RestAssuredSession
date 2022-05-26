package com.rest.api.authentication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.authentication.FormAuthConfig;
import org.testng.annotations.Test;

public class Preemptive_Auth_API_Test {

//    Basic:
//       preemptive
//       digest
//       form

    @Test
    public void basic_preemptive_Auth_Test(){
        given().log().all()
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
    public void basic_Auth_Test(){
        given().log().all()
                .auth()
                .basic("admin", "admin")
                .when().log().all()
                .get("https://the-internet.herokuapp.com/basic_auth")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void basic_Auth_Digest_Test(){
        given().log().all()
                .auth()
                .digest("admin", "admin")
                .when().log().all()
                .get("https://the-internet.herokuapp.com/basic_auth")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void basic_Auth_form_Test(){
        given().log().all()
                .auth()
                .form("dzmitryrazhkou@gmail.com", "3036057Dr",
                        new FormAuthConfig("https://classic.freecrm.com/system/authenticate.cfm",
                                "username", "password"))
                .when().log().all()
                .get("https://classic.freecrm.com/system/authenticate.cfm")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}
