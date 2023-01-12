package orderTests;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.qa_scooter.Data;
import org.qa_scooter.Order;
import org.qa_scooter.OrderHandles;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class CreateOrderTest {
    int idOrder;
    private OrderHandles orderHandles;
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        orderHandles = new OrderHandles();
    }

    @Test
    public void successCreateOrderWithTwoColors(){
        Order order = new Order (Data.FIRST_NAME, Data.LAST_NAME, Data.ADDRESS, Data.METRO_STATION, Data.PHONE, Data.RENT_TIME,
                Data.DELIVERY_DATE, Data.COMMENT, new String[]{Data.BLACK_COLOR, Data.GRAY_COLOR});
        ValidatableResponse orderResponse = orderHandles.createOrder(order).body("track", notNullValue());

        idOrder = orderResponse.extract().path("track");
        assertEquals(201, orderResponse.extract().statusCode());
    }
    @Test
    public void successCreateOrderWithBlackColor(){
        Order order = new Order (Data.FIRST_NAME, Data.LAST_NAME, Data.ADDRESS, Data.METRO_STATION, Data.PHONE, Data.RENT_TIME,
                Data.DELIVERY_DATE, Data.COMMENT, new String[]{Data.BLACK_COLOR});
        ValidatableResponse orderResponse = orderHandles.createOrder(order).body("track", notNullValue());
        idOrder = orderResponse.extract().path("track");
        assertEquals(201, orderResponse.extract().statusCode());
    }
    @Test
    public void successCreateOrderWithGreyColor(){
        Order order = new Order (Data.FIRST_NAME, Data.LAST_NAME, Data.ADDRESS, Data.METRO_STATION, Data.PHONE, Data.RENT_TIME,
                Data.DELIVERY_DATE, Data.COMMENT, new String[]{Data.GRAY_COLOR});
        ValidatableResponse orderResponse = orderHandles.createOrder(order).body("track", notNullValue());
        idOrder = orderResponse.extract().path("track");
        assertEquals(201, orderResponse.extract().statusCode());
    }
    @Test
    public void successCreateOrderWithoutColor(){
        Order order = new Order (Data.FIRST_NAME, Data.LAST_NAME, Data.ADDRESS, Data.METRO_STATION, Data.PHONE, Data.RENT_TIME,
                    Data.DELIVERY_DATE, Data.COMMENT, new String[]{});
        ValidatableResponse orderResponse = orderHandles.createOrder(order).body("track", notNullValue());
        idOrder = orderResponse.extract().path("track");
        assertEquals(201, orderResponse.extract().statusCode());
        }



}