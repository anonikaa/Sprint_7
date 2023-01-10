import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.qa_scooter.CreateCourier;

import static io.restassured.RestAssured.given;
public class createCourierTest {

    //вынести в класс с кредами
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void createCourier() {
        //эту дату тоже надо вынести в отдельный класс
        CreateCourier courier  = new CreateCourier ("login", "password", "name");
        given()
                //хидер тоже отдельно вынести
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/users/me");
                //добавить проверок на статус код
    }
}

