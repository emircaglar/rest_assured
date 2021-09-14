package GoRest;

import GoRest.All.Alle;
import GoRest.All.Pagination;
import GoRest.All.Link;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
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
    public void comments_all_responce_path() {

        String previus =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/comments")
                        .then()
                        //.log().body()
                        .extract().jsonPath().get("meta.pagination.links.previus");

        List<String> body_list =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/comments")
                        .then()
                        //.log().body()
                        .extract().jsonPath().getList("data.body");
        System.out.println("previus = " + previus);
        System.out.println("body_list = " + body_list);
    }

    @Test
    public void comments_all_responce() {

        Response response =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/comments")
                        .then()
                        //.log().body()
                        .extract().response();

        String previus = response.jsonPath().get("meta.pagination.links.previus");
        System.out.println("previus = " + previus);
        List<String> body_list = response.jsonPath().get("data.body");
        System.out.println("body_list = " + body_list);

    }

    @Test
    public void comments_all_lang_version() {

        Alle alle =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/comments")
                        .then()
                        //.log().body()
                        .extract().as(Alle.class);

        System.out.println("getCurrent = " + alle.getMeta().getPagination().getLinks().getCurrent());

    }

    int user_Id;

    public String mail_random() {
        return RandomStringUtils.randomAlphanumeric(8) + "@gmail.com";
    }

    @Test
    public void comments_create() {
        user_Id =
                given()
                        .when()
                        .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                        .contentType(ContentType.JSON)
                        //.pathParam("id",user_Id)
                        .body("{\n" +
                                "            \"post_id\": 4,\n" +
                                "            \"name\": \"Hamsini de Al Git\",\n" +
                                "            \"email\": \"" + mail_random() + "\",\n" +
                                "            \"body\": \"Fugit sed velit.\"\n" +
                                "        }")
                        .post("https://gorest.co.in/public/v1/comments")
                        .then()
                        .log().body()
                        .extract().jsonPath().get("data.id")
        //.body("code",201)
        ;
        System.out.println("user_Id = " + user_Id);

    }

    @Test(dependsOnMethods = "comments_create")
    public void comments_delete() {

        given()
                .when()
                .header("Authorization", "Bearer e77d719430c52f24f35e308c36023cfcd90108263e454b1fe8ebda8221624570")
                .pathParam("id", user_Id)
                .delete("https://gorest.co.in/public/v1/comments/{id}")
                .then()
                .statusCode(204)
        ;


    }
}
