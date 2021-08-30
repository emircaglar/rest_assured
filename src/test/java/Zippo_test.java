
import POJO.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

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
    public void bodyJsonPath() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("places[0].'place name'", equalTo("Beverly Hills"))// hir muss ich so('place name') schreiben, ansonsten sehe ich das
        //The parameter "name" was used but not defined.
        // Define parameters using the JsonPath.params(...) function
        ;
    }

    @Test
    public void has_size_number_check() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .body("places", hasSize(1))
                .log().body()
                .statusCode(200)
        ;
    }

    @Test
    public void combining_test() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .body("places[0].'place name'", equalTo("Beverly Hills"))
                .body("places.state", hasItem("California"))
                .body("places", hasSize(1))
        ;
    }

    @Test
    public void Path_parametre_test1() {
        given()
                .pathParam("country", "us")
                .pathParam("zipkod", "90210")
                .log().uri()
                .when()
                .get("http://api.zippopotam.us/{country}/{zipkod}")
                .then()
                .log().body()
                .body("places", hasSize(1))
                .body("places.state", hasItem("California"))
        ;
    }

    @Test
    public void Path_parametre_test2() {
        String country = "us";
        for (int i = 90210; i < 90214; i++) {
            given()
                    .pathParam("country", "us")
                    .pathParam("zipkod", i)
                    .log().uri()
                    .when()
                    .get("http://api.zippopotam.us/{country}/{zipkod}")
                    .then()
                    .log().body()
                    .body("places", hasSize(1))
                    .body("places.state", hasItem("California"))
            ;
        }
    }


    @Test
    public void Query_param_test() {

        given()
                .param("page", 1)
                .log().uri()
                .when()
                .get("https://gorest.co.in/public/v1/users")
                .then()
                //.log().body()
                .body("meta.pagination.page", equalTo(1))
        ;
    }

    @Test
    public void Query_param_test_for() {

        for (int page = 1; page < 6; page++) {
            given()
                    .param("page", page)
                    .log().uri()
                    .when()
                    .get("https://gorest.co.in/public/v1/users")
                    .then()
                    //.log().body()
                    .body("meta.pagination.page", equalTo(page))
            ;
        }

    }


    private ResponseSpecification responseSpecification;
    private RequestSpecification resquestSpecification;

    @BeforeClass
    public void setup() {

        baseURI = "http://api.zippopotam.us";

        resquestSpecification = new RequestSpecBuilder()
                .log(LogDetail.URI)
                .setAccept(ContentType.JSON)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();

    }

    @Test
    public void has_size_number_Url_mit_Befor() {
        given()

                .when()
                .get("/us/90210")// wir haben keine url , deswegen bekommen wir url von @BeforClass
                .then()
                .body("places", hasSize(1))
                .log().body()
                .statusCode(200)
        ;
    }

    @Test
    public void has_size_number_Url_mit_Befor_space() {
        given()
                .spec(resquestSpecification)
                .when()
                .get("/us/90210")// wir haben keine url , deswegen bekommen wir url von @BeforClass
                .then()
                .body("places", hasSize(1))
                .spec(responseSpecification);
    }


    @Test
    public void extracting_Json_String() {

        String place_name=// wir machen Strin und given methon gleich

        given()
                .spec(resquestSpecification)
                .when()
                .get("/us/90210")// wir haben keine url , deswegen bekommen wir url von @BeforClass
                .then()
                .spec(responseSpecification)
                //.body("places[0].'place name'", equalTo(""))// wir nehmen draußen
                .extract().path("places[0].'place name'")// extract muss immer am ende sein
               ;

        System.out.println("place name = " + place_name);


    }
    @Test
    public void Query_Cast_test_int() {
        int limit=
        given()
                //.log().uri()
                .when()
                .get("https://gorest.co.in/public/v1/users")
                .then()
                //.log().body()
                .extract().path("meta.pagination.limit" )
                ;
        System.out.println("limit = " + limit);
    }


    @Test
    public void Query_Cast_test_int_List() {
        List<Integer> listId =
                given()
                        //.log().uri()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .log().body()
                        .extract().path("data.id");
        System.out.println("listId = " + listId);
    }


    @Test
    public void extracting_Json_String_List() {

        List<String> Dorfen =// wir bekommen List von der Stadt Adana in der Turkei

                given()
                        .when()
                        .get("/tr/01000")// wir haben keine url , deswegen bekommen wir url von @BeforClass
                        .then()
                        //.body("places[0].'place name'", equalTo(""))// wir nehmen draußen
                        .extract().path("places.'place name'")// extract muss immer am ende sein
                ;

        System.out.println("Dorfen= " + Dorfen);
        Assert.assertTrue(Dorfen.contains("Sarihuğlar Köyü"));

    }

    @Test
    public void extracting_Json_String_POJO() {

        Location location =// wir bekommen List von der Stadt Adana in der Turkei

                given()
                        .when()
                        .get("/us/90210")// wir haben keine url , deswegen bekommen wir url von @BeforClass
                        .then()
                        //.body("places[0].'place name'", equalTo(""))// wir nehmen draußen
                        .extract().as(Location.class)// extract muss immer am ende sein
                ;

        System.out.println("location = " + location);
        System.out.println("location.getCountry() = " + location.getCountry());
        System.out.println("location.getPlaces() = " + location.getPlaces());
        System.out.println("location.getPostcode()= " + location.getPostcode());


    }


}
