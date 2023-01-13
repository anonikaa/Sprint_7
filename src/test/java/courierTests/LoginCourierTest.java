package courierTests;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.qa_scooter.Courier;
import org.qa_scooter.CourierCreds;
import org.qa_scooter.CourierHandles;
import org.qa_scooter.Data;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class LoginCourierTest {
    private Courier courier;
    private CourierHandles courierHandles;
    private CourierCreds courierCreds;
    public int id;
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        courierHandles = new CourierHandles();
    }
    @Test
    public void successCourierLogin(){
        courierCreds = new CourierCreds(Data.EXIST_LOGIN, Data.EXIST_PASSWORD); //передаем в креды наши логин с паролем
        ValidatableResponse loginResponse = courierHandles.login(courierCreds).body("id", notNullValue());
        assertEquals(200, loginResponse.extract().statusCode());

    }
    @Test
    public void wrongEmailCourierLogin(){
        courierCreds = new CourierCreds(Data.WRONG_EMAIL, Data.EXIST_PASSWORD);
        ValidatableResponse loginResponse = courierHandles.login(courierCreds);
        assertEquals(404,loginResponse.extract().statusCode());
        assertEquals("Учетная запись не найдена", loginResponse.extract().path("message"));
    }
    @Test
    public void wrongPasswordCourierLogin(){
        courierCreds = new CourierCreds(Data.EXIST_LOGIN, Data.WRONG_PASSWORD);
        ValidatableResponse loginResponse = courierHandles.login(courierCreds);
        assertEquals(404,loginResponse.extract().statusCode());
        assertEquals("Учетная запись не найдена", loginResponse.extract().path("message"));
    }

    @Test
    public void courierLoginWithoutEmail(){
        courierCreds = new CourierCreds(Data.EMPTY_FIELD, Data.EXIST_PASSWORD);
        ValidatableResponse loginResponse = courierHandles.login(courierCreds);
        assertEquals(400,loginResponse.extract().statusCode());
        assertEquals("Недостаточно данных для входа", loginResponse.extract().path("message"));
    }
    @Test
    public void courierLoginWithoutPassword(){
        courierCreds = new CourierCreds(Data.EXIST_LOGIN, Data.EMPTY_FIELD);
        ValidatableResponse loginResponse = courierHandles.login(courierCreds);
        assertEquals(400,loginResponse.extract().statusCode());
        assertEquals("Недостаточно данных для входа", loginResponse.extract().path("message"));
    }
    @Test
    public void notExistCourierLogin(){
        courierCreds = new CourierCreds(Data.WRONG_EMAIL, Data.WRONG_PASSWORD);
        ValidatableResponse loginResponse = courierHandles.login(courierCreds);
        assertEquals(404,loginResponse.extract().statusCode());
        assertEquals("Учетная запись не найдена", loginResponse.extract().path("message"));
    }
}
