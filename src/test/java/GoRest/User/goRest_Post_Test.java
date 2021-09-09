package GoRest.User;


import GoRest.Posts.Alle;
import GoRest.Posts.Data;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
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
        int limit=response.jsonPath().getInt("meta.pagination.limit");
        System.out.println("limit = " + limit);
        for (Data data:data_class
             ) {
            System.out.println("data = " + data);
        }
      /*  */
        

    }

    int _id ;
    @Test(priority = 2)
    public void goRestC_Post_create() {

        String title="bu gunler gecer1";
        String body="bu gunler tabiki gecer1";
    Data data=new Data();
    data.setTitle(title);
    data.setBody(body);

         Response response=
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .body(data)
                        .when()
                        .post("https://gorest.co.in/public/v1/users/87/posts")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .body("data.title",equalTo(title))
                        .body("data.body",equalTo(body))
                        .extract().response();


        _id=response.jsonPath().getInt("data.id");
        Assert.assertTrue(data.getTitle().equals(response.jsonPath().getString("data.title")));
        Assert.assertTrue(data.getBody().equals(response.jsonPath().getString("data.body")));

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
    @Test(dependsOnMethods = "goRestC_Post_create")
    public void goRestC_Post_aCreate_post() {

                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .pathParam("id",_id)
                        .when()
                        .get("https://gorest.co.in/public/v1/posts/{id}")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON)

                ;

    }


    @Test(dependsOnMethods = "goRestC_Post_create")
    public void goRestC_Post_aCreate_uodate() {

        String body="bu gunler tabiki gecer gecer de nasil gecer1";


        given()
                .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                .contentType(ContentType.JSON)
                .pathParam("id",_id)
                .body("{\"body\":\"" + body + "\"}")
                .when()
                .put("https://gorest.co.in/public/v1/posts/{id}")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
        .body("data.body",equalTo(body))

        ;

    }
}
