package GoRest.User;


import GoRest.Posts.Data;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class goRest_Post_Test {


    @Test(priority = 1)
    public void goRestC_Post_nested() {

        Response response =
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://gorest.co.in/public/v1/posts")
                        .then()
                        //.log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().response();

        List<Data> data_class=response.jsonPath().getList("data",Data.class);
        System.out.println("data_class = " + data_class);

    }

    int _id ;
    @Test(priority = 2)
    public void goRestC_Post_create() {
    Data data=new Data();
    data.setTitle("title1");
    data.setBody("title1 1111111");

         _id =
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)

                        .when()
                        .post("https://gorest.co.in/public/v1/posts")
                        .then()
                        //.log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().jsonPath().getInt("data.user_id");

        System.out.println("_id = " + _id);

    }

  ;
    @Test(priority = 2)
    public void goRestC_Post_a_post() {
        List<Data> postData=
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://gorest.co.in/public/v1/users/87/posts")
                        .then()
                        //.log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().jsonPath().getList("data",Data.class)
                ;
        for (Data d:postData
             ) {
            System.out.println("d = " + d);
        }

    }

}
