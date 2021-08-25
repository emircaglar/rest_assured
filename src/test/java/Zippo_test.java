
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
                .body("places[0].state", equalTo("California"))

                .statusCode(200)
        ;

    }


    @Test
    public void calofornia_hasitem() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("places.state", hasItem("California"))
                .statusCode(200)
        ;

    }

    @Test
    public void bodyJsonPath(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                 .log().body()
                 .body("places[0].'place name'",equalTo("Beverly Hills"))// hir muss ich so('place name') schreiben, ansonsten sehe ich das
                                                                               //The parameter "name" was used but not defined.
                                                                              // Define parameters using the JsonPath.params(...) function
        ;
    }

    @Test
    public void has_size_number_check(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .body("places",hasSize(1))
                .log().body()
                .statusCode(200)
        ;
    }

    @Test
    public void combining_test(){
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                 .body("places[0].'place name'",equalTo("Beverly Hills"))
                 .body("places.state",hasItem("California"))
                 .body("places",hasSize(1))
        ;
    }

    @Test
    public void Path_parametre_test1(){
        given()
                .pathParam("country","us")
                .pathParam("zipkod","90210")
                .log().uri()
                .when()
                .get("http://api.zippopotam.us/{country}/{zipkod}")
                .then()
                .log().body()
                .body("places",hasSize(1))
                .body("places.state",hasItem("California"))
        ;
    }

    @Test
    public void Path_parametre_test2(){
        String country="us";
        for (int i = 90210; i <90214 ; i++) {
            given()
                    .pathParam("country","us")
                    .pathParam("zipkod",i)
                    .log().uri()
                    .when()
                    .get("http://api.zippopotam.us/{country}/{zipkod}")
                    .then()
                    .log().body()
                    .body("places",hasSize(1))
                    .body("places.state",hasItem("California"))
            ;
        }
    }


    @Test
    public void Query_param_test(){

            given()
                    .param("page",1)
                    .log().uri()
                    .when()
                    .get("https://gorest.co.in/public/v1/users")
                    .then()
                    //.log().body()
                    .body("meta.pagination.page",equalTo(1))
            ;
        }
    @Test
    public void Query_param_test_for(){

        for (int page = 1; page < 6; page++) {
            given()
                    .param("page",page)
                    .log().uri()
                    .when()
                    .get("https://gorest.co.in/public/v1/users")
                    .then()
                    //.log().body()
                    .body("meta.pagination.page",equalTo(page))
            ;
        }

    }



}
