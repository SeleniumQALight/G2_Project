package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PrivatBankExchangePage;

import static libs.DriverHelper.getWebDriver;
import static libs.TestData.currencyDTOFromSite;
import static libs.TestData.currencyDTOFromAPI;

public class PrivatBankExchange_StepDefinition {
    private PrivatBankExchangePage exchangePage = new PrivatBankExchangePage(getWebDriver());

    @When("^User opens PrivatBankExchangePage$")
    public void userOpensPrivatBankExchangePage() {
        exchangePage.openLoginPage();
    }

    @Then("^User gets '(.*)' exchange course from 'PrivatBank' site$")
    public void userGetsCurrencyExchangeCourseFromPrivatBankSite(String currency) {
        exchangePage.getCurrencyFromPrivatByCode(currency);

    }

    @And("^User compare exchange course from 'PrivatBank' site and  ApiPrivatTest page$")
    public void userCompareExchangeCourseFromPrivatBankSiteAndApiPrivatTestPage() {
        exchangePage.compareCurrencyFromApiAndSite(currencyDTOFromAPI, currencyDTOFromSite);
    }
}

