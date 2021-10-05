package StepDefinitions;

import api.CcyDTO;
import api.EndPoints;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;
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
        Double currency_buy_api = pbMainPage.getCurrencyCurrentBuyExchangeViaAPIAndSave(currency);
        Double currency_sell_api = pbMainPage.getCurrencyCurrentSellExchangeViaAPIAndSave(currency);
    }


    @And("^Get '(.*)' current exchange from website$")
    public void getCurrencyCurrentExchangeFromWebsite(String currency) {
//        Double currency_buy_web = pbMainPage.getCurrencyCurrentBuyExchangeFromWebsiteAndSave(currency);
//        Double currency_sell_web = pbMainPage.getCurrencyCurrentSellExchangeFromWebsiteAndSave(currency);
    }
}
