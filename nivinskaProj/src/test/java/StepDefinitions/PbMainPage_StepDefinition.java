package StepDefinitions;

import api.CcyDTO;
import api.EndPoints;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import libs.TestData;
import org.junit.Assert;
import pages.PbMainPage;

import static io.restassured.RestAssured.given;
import static libs.DriverHelper.getWebDriver;

public class PbMainPage_StepDefinition {
    private PbMainPage pbMainPage = new PbMainPage(getWebDriver());

    @Given("^User opens 'PbMain' page$")
    public void userOpenPbMainPage() {
        pbMainPage.openPbMainPage();
    }

    @Given("^Get '(.*)' current exchange via API$")
    public void getCurrencyCurrentExchangeViaAPI(String currency) {
        TestData.currency_buy_api = pbMainPage.getCurrencyCurrentBuyExchangeViaAPIAndSave(currency);
        TestData.currency_sell_api = pbMainPage.getCurrencyCurrentSellExchangeViaAPIAndSave(currency);
    }


    @And("^Get '(.*)' current exchange from website$")
    public void getCurrencyCurrentExchangeFromWebsite(String currency) {
        TestData.currency_buy_web = pbMainPage.getCurrencyCurrentBuyExchangeFromWebsiteAndSave(currency);
        TestData.currency_sell_web = pbMainPage.getCurrencyCurrentSellExchangeFromWebsiteAndSave(currency);
    }

    @Then("^Compare current currency exchange via API and from website$")
    public void compareCurrentCurrencyExchangeViaAPIAndFromWebsite() {
        Assert.assertEquals("Currency buy exchange is not correct", TestData.currency_buy_api, TestData.currency_buy_web, 0);
        Assert.assertEquals("Currency sell exchange is not correct", TestData.currency_sell_api, TestData.currency_sell_api, 0);
    }
}
