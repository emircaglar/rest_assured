package GoRest;

import GoRest.Posts.Data;
import GoRest.Todos.Datam;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GoRest_Todo {

    @Test
    public void goRestC_Post_nested() {

        Response response =
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://gorest.co.in/public/v1/todos")
                        .then()
                        //.log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().response();

        List<Datam> data_class = response.jsonPath().getList("data", Datam.class);
       /* int limit = response.jsonPath().getInt("meta.pagination.limit");
        System.out.println("limit = " + limit);*/
        for (Datam data : data_class
        ) {
            System.out.println("data = " + data);
        }
    }
}