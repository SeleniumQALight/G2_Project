package pages;

import api.CcyDTO;
import api.EndPoints;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.restassured.RestAssured.given;

public class PbMainPage extends ParentPage {
    @FindBy(xpath = ".//*[@id='EUR_buy']")
    private WebElement EUR_buy;

    @FindBy(xpath = ".//*[@id='EUR_sell']")
    private WebElement EUR_sell;

    @FindBy(xpath = ".//*[@id='USD_buy']")
    private WebElement USD_buy;

    @FindBy(xpath = ".//*[@id='USD_sell']")
    private WebElement USD_sell;

    @FindBy(xpath = ".//*[@id='RUB_buy']")
    private WebElement RUB_buy;

    @FindBy(xpath = ".//*[@id='RUB_sell']")
    private WebElement RUB_sell;

    public PbMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    //???
    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openPbMainPage() {
        try {
            webDriver.get("https://privatbank.ua/");
            logger.info("PbMain page was opened");
        } catch (Exception e) {
            logger.error("Can not work with PbMainPage" + e);
            Assert.fail("Can not work with PbMainPage");
        }
    }

    public Double getCurrencyCurrentBuyExchangeViaAPIAndSave(String currency) {
        CcyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(EndPoints.CCY_EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CcyDTO[].class);

        Double currency_buy = null;
        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy() == currency) {
                currency_buy = Double.parseDouble(responseBody[i].getBuy());
            }
            break;
        }
        return currency_buy;
    }

    public Double getCurrencyCurrentSellExchangeViaAPIAndSave(String currency) {
        CcyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(EndPoints.CCY_EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CcyDTO[].class);

        Double currency_sell = null;
        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy() == currency) {
                currency_sell = Double.parseDouble(responseBody[i].getSale());
            }
            break;
        }
        return currency_sell;
    }
}
