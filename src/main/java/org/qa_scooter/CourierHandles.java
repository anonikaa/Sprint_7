package org.qa_scooter;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierHandles {
    public ValidatableResponse create (Courier courier){
      return given()
              .header("Content-type", "application/json")
              .body(courier)
              .when()
              .post("/api/v1/courier").then();

    }

    public ValidatableResponse login (CourierCreds creds){
        return given()
                .header("Content-type", "application/json")
                .body(creds)
                .when()
                .post("/api/v1/courier/login").then();

    }
    public ValidatableResponse delete (int id){
        return given()
                .header("Content-type", "application/json")
                //.body()
                .when()
                .delete("/api/v1/courier/" + id).then();

    }


}
