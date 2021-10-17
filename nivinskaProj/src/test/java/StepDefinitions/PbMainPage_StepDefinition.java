package StepDefinitions;

import api.ApiHelper;
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
    private ApiHelper apiHelper = new ApiHelper();

    @Given("^User opens 'PbMain' page$")
    public void userOpenPbMainPage() {
        pbMainPage.openPbMainPage();
    }

    @Given("^Get '(.*)' current exchange via API$")
    public void getCurrencyCurrentExchangeViaAPI(String currency) {
        apiHelper.getCurrencyCurrentExchangeViaAPI(currency);
    }


    @And("^Get '(.*)' current exchange from website$")
    public void getCurrencyCurrentExchangeFromWebsite(String currency) {
        pbMainPage.getCurrencyCurrentExchangeFromWebsite(currency);
    }

    @Then("^Compare current currency exchange via API and from website$")
    public void compareCurrentCurrencyExchangeViaAPIAndFromWebsite() {
        pbMainPage.compareCurrentCurrencyExchangeViaAPIAndFromWebsite();
    }
}
