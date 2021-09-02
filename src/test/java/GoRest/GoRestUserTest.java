package GoRest;


import GoRest.User.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GoRestUserTest {
/*
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
*/
    int UserId;

    @Test(priority = 1)
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

    @Test(priority = 2)
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
    @Test(priority = 3)
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
    @Test(priority = 4)
    public void delete_user() {

        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                .pathParam("id", UserId)
                .when()
                .delete("https://gorest.co.in/public/v1/users/{id}")
                //https://gorest.co.in/public/v1/users/1877 pathParam
                //https://gorest.co.in/public/v1/users/%7Bid%7D?id=1889    param
                .then()
                .statusCode(204)
        ;
        System.out.println("silindi");
    }


    @Test(priority = 5)
    public void negati_delete_user() {

        given()
                .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                .pathParam("userID",UserId)

                .when()
                .delete("https://gorest.co.in/public/v1/users/{userID}")

                .then()
                .statusCode(404)
        ;
        System.out.println(UserId+"tekrar silemedik");
    }


    @Test
    public void responsSample(){

        Response response=
                given()
                .when()
                .get("https://gorest.co.in/public/v1/users")
                .then()
                .log().body()
                .extract().response()
                ;

        List<User>userList=response.jsonPath().getList("data",User.class);
        int total=response.jsonPath().getInt("meta.pagination.total");
        int limit=response.jsonPath().getInt("meta.pagination.limit");
        User firstUser=response.jsonPath().getObject("data[0]",User.class);

        System.out.println("firstUser = " + firstUser);
        System.out.println("limit = " + limit);
        System.out.println("total = " + total);
        System.out.println("userList = " + userList);


    }



    @Test
    public void create_User_HashMap() {
     Map<String,String> newUser=new HashMap<>();
     newUser.put("name","mert can");
     newUser.put("gender","male");
     newUser.put("status","active");
     newUser.put("email",getEmail());

        UserId =
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .body(newUser)
                        .when()
                        .post("https://gorest.co.in/public/v1/users")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getInt("data.id")
        ;
        //System.out.println("UserId = " + UserId);
    }

    @Test
    public void create_User_Body_Object() {
        User user=new User();
        user.setName("mahirrr");
        user.setGender("male");
        user.setStatus("active");
        user.setEmail(getEmail());

        UserId =
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .body(user)
                        .log().body()
                        .when()
                        .post("https://gorest.co.in/public/v1/users")
                        .then()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().jsonPath().getInt("data.id")
        ;

    }



    public String getEmail() {
        String random = RandomStringUtils.randomAlphabetic(8).toLowerCase();
        return random + "@gmail.com";
    }
}
