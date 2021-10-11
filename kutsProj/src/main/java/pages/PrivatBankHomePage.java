package pages;

import api.CurrencyDTO;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Select;

import static api.EndPoints.EXCHANGE_RATES;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PrivatBankHomePage extends ParentPage{

    Actions actions = new Actions(webDriver);

    @FindBy(xpath = ".//select[@id='courses_type']")
    private Select dropDownCoursesType;

    private Double value_double, scale, result, value_api_double;
    private String currency_ui;

    public PrivatBankHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    @Step
    public void openPrivatBankHomePage(){
        try {
            webDriver.get(baseUrlPrivatBank);
            logger.info("PrivatBank Home page was opened");
        }catch (Exception e){
            logger.error("Can not work with PrivatBank Home page" + e);
            Assert.fail("Can not work with PrivatBank Home page");
        }
    }

    public void scrollToExchangeRatesBlock() {
        try {
            WebElement blockExchangeRates = webDriver.findElement(By.xpath(".//*[@class='block_content courses']"));
            actions.moveToElement(blockExchangeRates).perform();
            logger.info("Exchange rates block is displayed");
        }catch (Exception e){
            logger.error("Can not work with Exchange rates block");
            Assert.fail("Can not work with Exchange rates block");
        }
    }

    public void selectInBranchesValueInDDCoursesType() {
        selectValueInDD(dropDownCoursesType, "posts_course");
    }

    public Double getExchangeBuyingRate(String currency) {
        CurrencyDTO[] responseBody = given()
                .contentType(JSON)
                .log().all()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .when()
                .get(EXCHANGE_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equalsIgnoreCase(currency)) {
                value_api_double = convertStringToDouble(responseBody[i].getBuy());
            }
        }
        return value_api_double;
    }

    public Double getExchangeSellingRate(String currency) {
        CurrencyDTO[] responseBody = given()
                .contentType(JSON)
                .log().all()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .when()
                .get(EXCHANGE_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equalsIgnoreCase(currency)) {
                value_api_double = convertStringToDouble(responseBody[i].getSale());
            }
        }
        return value_api_double;
    }

    public Double convertStringToDouble(String value) {
        value_double = Double.parseDouble(value);
        scale = Math.pow(10, 2);
        result = Math.ceil(value_double * scale) / scale;
        return result;
    }

    public void checkExchangeBuyingRate(String currency) {
        if (currency.equalsIgnoreCase("RUR")) {
            currency_ui = "RUB";

            Assert.assertEquals(convertStringToDouble(webDriver.findElement(By.xpath(String.format(".//*[@id='%s_buy']", currency_ui))).getText().trim()),
                    getExchangeBuyingRate(currency));
        } else {
            Assert.assertEquals(convertStringToDouble(webDriver.findElement(By.xpath(String.format(".//*[@id='%s_buy']", currency))).getText().trim()),
                    getExchangeBuyingRate(currency));
        }
    }

    public void checkExchangeSellingRate(String currency) {
        if (currency.equalsIgnoreCase("RUR")) {
            currency_ui = "RUB";

            Assert.assertEquals(convertStringToDouble(webDriver.findElement(By.xpath(String.format(".//*[@id='%s_sell']", currency_ui))).getText().trim()),
                    getExchangeSellingRate(currency));
        } else {
            Assert.assertEquals(convertStringToDouble(webDriver.findElement(By.xpath(String.format(".//*[@id='%s_sell']", currency))).getText().trim()),
                    getExchangeSellingRate(currency));
        }
    }
}
