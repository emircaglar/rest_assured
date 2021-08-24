
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Zippo_test {

    @Test
    public void test(){
        given()// hazirlik

        .when()// link ve aksiyon

        .then()// test ve extract
        ;
    }
     @Test
    public void statusCodeTest(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()// man kann alle body schauen
                .statusCode(200)//Wenn wir 201 schreiben, bekommen wir dieses Satz :Expected status code <201> but was <200>.
        ;


    }
}
