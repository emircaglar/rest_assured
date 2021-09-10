package GoRest;

import GoRest.Posts.Data;

import GoRest.Todos1.DataT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GoRest_Todo {
    int id = 0;
    int todos;
    @Test
    public void goRestC_todos_pages() {
        todos =
                given()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://gorest.co.in/public/v1/todos")
                        .then()
                        //.log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().jsonPath().getInt("meta.pagination.pages");

    }

    @Test(dependsOnMethods = "goRestC_todos_pages")
    public void goRestC_Todos_nested() {
        for (int i = 1; i <=todos ; i++) {
            Response response =
                    given()
                            .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                            .contentType(ContentType.JSON)
                            .pathParam("todos_nummer", i)
                            .when()
                            .get("https://gorest.co.in/public/v1/todos?page={todos_nummer}")
                            .then()
                            //.log().body()
                            .statusCode(200)
                            .contentType(ContentType.JSON)
                            .extract().response();
            List<DataT> data_class = response.jsonPath().getList("data", DataT.class);

            for (int j = 0; j < data_class.size(); j++) {
                if (data_class.get(j).getId() > id)
                    id = data_class.get(j).getId();
            }


        }  System.out.println(id);
        }

}