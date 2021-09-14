package Basqar;

import Basqar.Model.Country;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Country_create {
    Cookies cookies;

    @BeforeClass
    public void Login() {
        baseURI = "https://demo.mersys.io";
        Map<String, String> create = new HashMap<>();
        create.put("username", "richfield.edu");
        create.put("password", "Richfield2020!");
        create.put("rememberMe", "true");
        cookies =
                given()
                        .body(create)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("https://demo.mersys.io/auth/login")
                        .then()
                        //.log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON)

                        .extract().response().getDetailedCookies()
        ;

        System.out.println("cookies = " + cookies);

    }

    @Test
    public void Create_Country() {
         String Randomname=RandomStringUtils.randomAlphabetic(6)+"qq";
         String Randomcode=RandomStringUtils.randomAlphabetic(4);


        Country country = new Country();
        country.setName(Randomname);
        country.setCode(Randomcode);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(country)
                .when()
                .post("/school-service/api/countries")
                .then()
                .log().body()
                .statusCode(201)
                .body("name",equalTo(Randomname))

        ;


    }
}
