import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.qa_scooter.Courier;
import org.qa_scooter.CourierCreds;
import org.qa_scooter.CourierHandles;

import static org.junit.Assert.assertEquals;
import static org.qa_scooter.Data.*;

public class CreateCourierTest {
    private Courier courier;
    private CourierHandles courierHandles;
    private CourierCreds courierCreds;
    public int id;
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        courierHandles = new CourierHandles();
    }
    //дописать месседж ок true
    @Test
    public void successCreateCourier() {
        courier = new Courier(DEFAULT_LOGIN, DEFAULT_PASSWORD, DEFAULT_NAME);
        courierCreds = new CourierCreds(courier.getLogin(), courier.getPassword()); //получаем креды именно этого курьера для логина

        ValidatableResponse response = courierHandles.create(courier);
        assertEquals(201, response.extract().statusCode());
        ValidatableResponse loginResponse = courierHandles.login(courierCreds); //логин нужен для получения айдишника
        id = loginResponse.extract().path("id"); //айдишник нужен чтобы почистить за собой
        courierHandles.delete(id);
    }
    @Test
    public void cantCreateTheSameCourier(){
        courier  = new Courier(EXIST_LOGIN, DEFAULT_PASSWORD, DEFAULT_NAME);
        ValidatableResponse response = courierHandles.create(courier);
        assertEquals(409, response.extract().statusCode());
        assertEquals("Этот логин уже используется", response.extract().path("message"));
    }
    @Test
    public void cantCreateCourierWithoutLogin(){
        courier  = new Courier(EMPTY_FIELD, DEFAULT_PASSWORD, DEFAULT_NAME);
        ValidatableResponse response = courierHandles.create(courier);
        assertEquals(400, response.extract().statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.extract().path("message"));
    }
    @Test
    public void cantCreateCourierWithoutPassword(){
        courier  = new Courier(DEFAULT_LOGIN, EMPTY_FIELD, DEFAULT_NAME);
        ValidatableResponse response = courierHandles.create(courier);
        assertEquals(400, response.extract().statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.extract().path("message"));
    }
    @Test
    public void successCreateCourierWithoutName(){
        courier  = new Courier(DEFAULT_LOGIN, DEFAULT_PASSWORD, EMPTY_FIELD);
        courierCreds = new CourierCreds(courier.getLogin(), courier.getPassword()); //получаем креды именно этого курьера для логина
        ValidatableResponse response = courierHandles.create(courier);
        assertEquals(201, response.extract().statusCode());
        ValidatableResponse loginResponse = courierHandles.login(courierCreds); //логин нужен для получения айдишника
        id = loginResponse.extract().path("id"); //айдишник нужен чтобы почистить за собой
        courierHandles.delete(id);
    }

}

