
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Zippo_test {

    @Test
    public void test() {
        given()// hazirlik

                .when()// link ve aksiyon

                .then()// test ve extract
        ;
    }

    @Test
    public void statusCodeTest() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()// man kann alle body schauen
                .statusCode(200)//Wenn wir 201 schreiben, bekommen wir dieses Satz
        // :Expected status code <201> but was <200>.
        ;


    }

    @Test
    public void content_Typ_test() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .contentType(ContentType.JSON)//wenn wir text schreiben, bekommen wir dieses satz
        // :Expected content-type "TEXT" doesn't match actual content-type "application/json".
        ;

    }

    @Test
    public void logTest() {
        given()
                .log().all()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()


        ;

    }

    @Test
    public void checkStateinResponsableBody() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("country", equalTo("United States"))//JSON path country doesn't match.
                                                               //Expected: United Statesss
                                                              //Actual: United States
                .statusCode(200)
        ;

    }

    @Test
    public void calofornia() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("places[0].state", equalTo("California"))//JSON path country doesn't match.
                //Expected: United Statesss
                //Actual: United States
                .statusCode(200)
        ;

    }

}
