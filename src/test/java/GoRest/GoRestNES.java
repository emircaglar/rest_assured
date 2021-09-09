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

    @Test(priority = 1)
    public void goRestComments_nested() {
        Data d = new Data();
        d.setName(name_nachname());
        d.setEmail(getEmail());
        d.setBody(comment());
        _id =
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .body(d)
                        .when()
                        .post("https://gorest.co.in/public/v1/posts/55/comments")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().jsonPath().getInt("data.id")
        ;
        System.out.println("id = " + _id);

    }


    @Test(priority = 2)
    public void goRestComments_nested_put() {

        String body1 = "comment()";
        String body =
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .pathParam("id", _id)
                        .body("{\"body\":\"" + body1 + "\"}")
                        .when()
                        .put("https://gorest.co.in/public/v1/comments/{id}")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("data.body", equalTo(body1))
                        .extract().jsonPath().getString("data.body");
        Assert.assertTrue(body.equalsIgnoreCase(body1));

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
                .delete("https://gorest.co.in/public-api/comments/{id}")
                //("https://gorest.co.in/public-api/todos/{todoId}")
                .then()
                //.statusCode(404)
                .body("code", equalTo(404));
        ;

    }

    public String getEmail() {
        String random = RandomStringUtils.randomAlphabetic(8).toLowerCase();
        return random + "@gmail.com";
    }

    public String name_nachname() {
        String random = RandomStringUtils.randomAlphabetic(5).toLowerCase();
        return random;
    }

    public String comment() {
        String random = RandomStringUtils.randomAlphabetic(5).toLowerCase();
        return random + random + random;
    }
}
