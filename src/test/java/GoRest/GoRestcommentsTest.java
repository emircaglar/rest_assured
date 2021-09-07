package GoRest;

import GoRest.All.Pagination;
import GoRest.All.Link;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GoRestcommentsTest {

    @Test
    public void goRestComments() {
        Response response =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/comments")
                        .then()
                        //.log().body()
                        .extract().response();
        List<Pagination> datalist = response.jsonPath().getList("data", Pagination.class);

        for (Pagination data : datalist
        ) {
            System.out.println("data = " + data);
        }
    }


    @Test
    public void Email_Test() {
        Response response =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/comments")
                        .then()
                        .extract().response();
        List<String> emailList = response.jsonPath().getList("data.email");
        Assert.assertTrue(emailList.contains("dubashi_lavanya@murphy.com"));
    }

    @Test
    public void Email_Test_mit_path() {
        List<String> email_List =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/comments")
                        .then()
                        .extract().path("data.email");
        System.out.println("email_List = " + email_List);
        Assert.assertTrue(email_List.contains("dubashi_lavanya@murphy.com"));
    }

    @Test
    public void comments_all_responce() {

        Response response=
        given()
                .when()
                .get("https://gorest.co.in/public/v1/comments")
                .then()
               //.log().body()
                .extract().response()
        ;
        
      String previus=response.jsonPath().get("meta.pagination.links.previus");
        System.out.println("previus = " + previus);
        List<String>body_list=response.jsonPath().get("data.body");
        System.out.println("body_list = " + body_list);
    }
}
