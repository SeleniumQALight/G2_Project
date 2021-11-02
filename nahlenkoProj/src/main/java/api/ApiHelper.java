package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;

import static api.EndPoints.LOGIN;
import static api.EndPoints.POST_BY_USER;
import static api.EndPointsPrivat.GET_Currency;
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
                .get(POST_BY_USER, userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);
    }

    public void deletePostsTillPresent(String userName, String passWord) { //получение списка постов и удаление конкретного поста
        PostDTO[] listOfPosts = getAllPostsByUser(userName);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(userName, passWord, listOfPosts[i].get_id());
            logger.info(String.format("Post with id%s and title %s was deleted" //метод формат %s заменяет на то что после запятой
                    , listOfPosts[i].get_id()
                    , listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number Of posts", 0, getAllPostsByUser(userName).length);
    }

    private void deletePostById(String userName, String passWord, String id) { //метод удаляющий пост
        String token = getToken(userName, passWord);

        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("token", token);

        given()
                .spec(requestSpecification)
                .body(bodyRequest.toMap())
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .statusCode(200).log().all();

    }

    public void createPost(String title, String userName, String passWord) {
        String token = getToken(userName, passWord);

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New post from API Vika");
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");
        requestParams.put("token", token);


        given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap())
                .log().all()
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200);
    }

    public CurrencyDTO[] getCurrencyExchangePrivat() {
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all().queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .when()
                .get(GET_Currency)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

        for (int i = 0; i < responseBody.length; i++) { //вывести в консоль "Курс RUB к UAH покупки 0,28000 и продажи 0,32000"(имя валют и курсы взять с респонса) и так для каждой валюты
            System.out.println("Курс " + responseBody[i].getCcy() + " к " + responseBody[i].getBase_ccy() + " покупки " + responseBody[i].getBuy() + " и " + responseBody[i].getSale() + " продажи");

        }
        CurrencyDTO[] expectedCurrencyDTO = {
                new CurrencyDTO("USD", "UAH", "26.50000", "26.90000"),
                new CurrencyDTO("EUR", "UAH", "26.50000", "26.90000"),
                new CurrencyDTO("RUR", "UAH", "26.50000", "26.90000"),
                new CurrencyDTO("BTC", "USD", "26.50000", "26.90000"),
        };
        Assert.assertEquals(responseBody.length, expectedCurrencyDTO.length);//сравнение наполнения
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedCurrencyDTO.length; i++) {
            softAssertions.assertThat(expectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");//игнорим для проверки поля buy и sale

        }
        softAssertions.assertAll();
        return responseBody;
    }
}