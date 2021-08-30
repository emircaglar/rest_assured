import POJO.List_POJO;
import POJO.Location;
import POJO.Pj;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Task {

    /**
     * Task 1
     * create a request to https://httpstat.us/203
     * expect status 203
     * expect content type TEXT
     */

    @Test
    public void test1() {

        given()
                .when()
                .get(" https://httpstat.us/203")
                .then()
                .statusCode(203)
                .contentType(ContentType.TEXT)

        ;

    }

    /**
     * Task 2
     * create a request to https://httpstat.us/203
     * expect status 203
     * expect content type TEXT
     * expect BODY to be equal to "203 Non-Authoritative Information"
     */

    @Test
    public void test2() {

        given()
                .when()
                .get(" https://httpstat.us/203")
                .then()
                .statusCode(203)
                .body(equalTo("203 Non-Authoritative Information"))
        ;
    }
    /** Task 3
     *  create a request to https://jsonplaceholder.typicode.com/todos/2
     *  expect status 200
     *  expect content type JSON
     *  expect title in response body to be "quis ut nam facilis et officia qui"
     */
    @Test
    public void test3() {

        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",equalTo("quis ut nam facilis et officia qui"))
        ;
    }
    /** Task 4
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     *  expect status 200
     *  expect content type JSON
     *  expect response completed status to be false
     */
    @Test
    public void test4() {

        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body()
                .body("completed",equalTo(false))
        ;
    }

    /** Task 5
     * create a request to https://jsonplaceholder.typicode.com/todos
     * expect status 200
     * expect content type JSON
     * expect third item have:
     *      title = "fugiat veniam minus"
     *      userId = 1
     */
    @Test
    public void test5() {

        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body()
                .body("title",hasItem("fugiat veniam minus"))
                .body("userId",hasItem(1))
        ;
    }
    /** Task 6
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * Converting Into POJO
     */

    @Test
    public void test6() {
       Pj pj=
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body()
                .extract().as(Pj.class)
        ;

        System.out.println("pj.getId() = " + pj.getId());
        System.out.println("pj.getTitle() = " + pj.getTitle());
        System.out.println("pj.isCompleted() = " + pj.isCompleted());
        System.out.println("pj.getUserId() = " + pj.getUserId());

    }

    /** Task 7
     * create a request to https://jsonplaceholder.typicode.com/todos
     * expect status 200
     * Converting Array Into Array of POJOs
     */
    @Test
    public void test7() {
      
        List_POJO list_pojo=
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                       // .log().body()
                         .extract().as(List_POJO.class)
                ;

        for (int i = 0; i <list_pojo.getPj_list().size(); i++) {
            System.out.println(list_pojo.getPj_list().get(i).getId());
        }

    }

    @Test
    public void test8() {

        List<String> pojo=
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        // .log().body()
                        .extract().path("title")
                ;

        for (int i = 0; i <pojo.size() ; i++) {
            System.out.println( pojo.get(i));
        }

    }


}
