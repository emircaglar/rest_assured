package GoRest;


import GoRest.All.Data;
import GoRest.All.Pagination;
import GoRest.User.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestNES {
    int _id;
    @Test (priority = 1)
    public void goRestComments_nested() {
        Data d=new Data();
        d.setName("ertan");
        d.setEmail(getEmail());
        d.setBody("ddddd");
     _id=
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .body(d)
                        .when()
                        .post("https://gorest.co.in/public/v1/posts/68/comments")
                        .then()
                        .log().body()
                         .extract().jsonPath().getInt("data.id")
                ;
        System.out.println("id = " + _id);

    }


    @Test(priority = 2)
    public void goRestComments_nested_put() {

        String body1 = "aha la";
        String body=
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .pathParam("id",_id)
                        .body("{\"body\":\"" + body1 + "\"}")
                        .when()
                        .put("https://gorest.co.in/public/v1/comments/{id}")
                        .then()
                        .log().body()
                       // .body("body.data",equalTo(body1))
                        .extract().jsonPath().getString("data.body")

                ;
        //Assert.assertTrue(body.equalsIgnoreCase(d.getBody()));

    }


    @Test(priority = 3)
    public void goRestComments_nested_delete() {

       given()
                .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                .pathParam("id", _id)
                .when()
                .delete("https://gorest.co.in/public/v1/comments/{id}")
               .then()
                .statusCode(204);
    }

    @Test(priority = 4)
    public void goRestComments_nested_delete_negtiv() {

        given()
                .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                .pathParam("id", _id)
                .when()
                .delete("https://gorest.co.in/public/v1/comments/{id}")
                .then()
                .statusCode(404);
    }

    public String getEmail() {
        String random = RandomStringUtils.randomAlphabetic(8).toLowerCase();
        return random + "@gmail.com";
    }


}
