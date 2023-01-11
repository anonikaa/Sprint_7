import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.qa_scooter.CreateCourier;
import org.qa_scooter.Data.*;
import static org.junit.Assert.assertEquals;
import static org.qa_scooter.Data.*;

public class createCourierTest {
    @Before
    public void setUp() {
        //вынести в отдельный класс
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
    @Test
    public void successCreateCourier() {
        CreateCourier courier  = new CreateCourier(DEFAULT_LOGIN, DEFAULT_PASSWORD, DEFAULT_NAME);
        ValidatableResponse response = courier.create(courier);
        assertEquals(201, response.extract().statusCode());
    }
    @Test
    public void cantCreateTheSameCourier(){
        CreateCourier courier  = new CreateCourier(DEFAULT_LOGIN, DEFAULT_PASSWORD, DEFAULT_NAME);
        ValidatableResponse response = courier.create(courier);
        assertEquals(409, response.extract().statusCode());
        assertEquals("Этот логин уже используется", response.extract().path("message"));
    }
    @Test
    public void cantCreateCourierWithoutLogin(){
        CreateCourier courier  = new CreateCourier(EMPTY_FIELD, DEFAULT_PASSWORD, DEFAULT_NAME);
        ValidatableResponse response = courier.create(courier);
        assertEquals(400, response.extract().statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.extract().path("message"));
        String message = response.extract().path("message");
        System.out.println(message);
    }
    @Test
    public void cantCreateCourierWithoutPassword(){
        CreateCourier courier  = new CreateCourier(DEFAULT_LOGIN, EMPTY_FIELD, DEFAULT_NAME);
        ValidatableResponse response = courier.create(courier);
        assertEquals(400, response.extract().statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.extract().path("message"));
        String message = response.extract().path("message");
        System.out.println(message);
    }
    @Test
    public void cantCreateCourierWithoutName(){
        CreateCourier courier  = new CreateCourier(DEFAULT_LOGIN, DEFAULT_PASSWORD, EMPTY_FIELD);
        ValidatableResponse response = courier.create(courier);
        assertEquals(400, response.extract().statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.extract().path("message"));
        String message = response.extract().path("message");
        System.out.println(message);
    }

}

