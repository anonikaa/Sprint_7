package org.qa_scooter;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderHandles {
    public ValidatableResponse createOrder(Order order){
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders").then();
    }
    public ValidatableResponse getOrder(){
        return given()
                .header("Content-type", "application/json")
                //.body()
                .when()
                .get("/api/v1/orders").then();
    }
}
