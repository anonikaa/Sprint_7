package org.qa_scooter;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CreateCourier {
    String login;
    String password;
    String firstName;

    public CreateCourier (String login, String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public CreateCourier() {
    }

    public CreateCourier(String defaultLogin) {
    }

    public ValidatableResponse create (CreateCourier courier){
      return given()
              .header("Content-type", "application/json")
              .body(courier)
              .when()
              .post("/api/v1/courier").then();

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
