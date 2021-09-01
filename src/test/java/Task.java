import POJO.Todo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
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
       Todo name=
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body()
                .extract().as(Todo.class)
        ;

        System.out.println("pj.getId() = " + name.getId());
        System.out.println("pj.getTitle() = " + name.getTitle());
        System.out.println("pj.isCompleted() = " + name.isCompleted());
        System.out.println("pj.getUserId() = " + name.getUserId());

    }


    /** Task 7
     * create a request to https://jsonplaceholder.typicode.com/todos
     * expect status 200
     * Converting Array Into Array of POJOs
     */
    @Test
    public void test7() {

        Todo[] todo=
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                       .log().body()
                         .extract().as(Todo[].class)
                ;

        System.out.println("Arrays.toString(todo) = " + Arrays.toString(todo));

    }


    /** Task 8
     * create a request to https://jsonplaceholder.typicode.com/todos
     * expect status 200
     * Converting Array Into List of POJOs
     */
    @Test
    public void test8() {
        Todo[] todo=
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().as(Todo[].class)
                ;

        List<Todo> todolist=Arrays.asList(todo);
        System.out.println("Arrays.toString(todo) = " + Arrays.toString(todo));// ist ist nicht Array
        System.out.println("todolist = " + todolist);
    }


}
