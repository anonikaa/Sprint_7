package orderTests;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.qa_scooter.OrderHandles;

import static org.junit.Assert.assertEquals;


public class GetOrderTest {
    private OrderHandles orderHandles;
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        orderHandles = new OrderHandles();
    }
    @Test
    public void getOrder(){
        ValidatableResponse getOrderResponse = orderHandles.getOrder();
        assertEquals(200, getOrderResponse.extract().statusCode());
    }
}
