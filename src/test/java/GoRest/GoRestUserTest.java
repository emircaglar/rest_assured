package GoRest;


import GoRest.User.User;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GoRestUserTest {

    @Test
    public void getUsers() {


        List<User> user_List =
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .log().body()
                        .extract().jsonPath().getList("data", User.class);

        for (User user : user_List
        ) {
            System.out.println("user = " + user);
        }
    }

    int UserId;

    @Test
    public void create_User() {

        UserId =
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .body("{\"name\":\"merthan aga\", \"gender\":\"male\", \"email\":\"" + getEmail() + "\", \"status\":\"active\"}")
                        .when()
                        .post("https://gorest.co.in/public/v1/users")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getInt("data.id")
        ;
        //System.out.println("UserId = " + UserId);
    }
    @Test(dependsOnMethods = "create_User")
    public void get_User() {
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .pathParam("id", UserId)
                .when()
                .get("https://gorest.co.in/public/v1/users/{id}")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.id", equalTo(UserId))
        ;
    }
    @Test(dependsOnMethods = "create_User")
    public void update_user() {

        String isim="daniel";
        given()
                .contentType(ContentType.JSON)
                //.log().body()
                .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                .body("{\"name\":\""+isim+"\"}")
                .pathParam("id", UserId)
                .when()
                .put("https://gorest.co.in/public/v1/users/{id}")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.name", equalTo(isim))
        ;
    }


    public String getEmail() {
        String random = RandomStringUtils.randomAlphabetic(8).toLowerCase();
        return random + "@gmail.com";
    }
}
