package api;

import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static api.EndPoints.LOGIN;
import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;


public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();
        return responseBody.asString().replaceAll("\"", "");
    }

    public PostDTO[] getAllPostsByUser(String userName) {//возвращает все посты нашего пользователя
        return given()
                .spec(requestSpecification)// собраны хедеры
                .when()
                .get(POST_BY_USER,userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);
    }

    public void deletePostsTillPresent(String userName, String passWord) { //получение списка постов и удаление конкретного поста
        PostDTO[] listOfPosts=getAllPostsByUser(userName);
        for (int i=0; i<listOfPosts.length; i++){
            deletePostById(userName,passWord,listOfPosts[i].get_id());
            logger.info(String.format("Post with id%s and title %s was deleted" //метод формат %s заменяет на то что после запятой
                ,listOfPosts[i].get_id()
                ,listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number Of posts",0,getAllPostsByUser(userName).length);
    }

    private void deletePostById(String userName, String passWord, String id) { //метод удаляющий пост
        String token=getToken(userName,passWord);

        JSONObject bodyRequest=new JSONObject();
        bodyRequest.put("token",token);

        given()
                .spec(requestSpecification)
                .body(bodyRequest.toMap())
        .when()
                .delete(EndPoints.DELETE_POST,id)
        .then()
                .statusCode(200).log().all();

    }
}