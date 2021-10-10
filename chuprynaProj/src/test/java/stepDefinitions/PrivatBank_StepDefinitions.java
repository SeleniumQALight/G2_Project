package stepDefinitions;

import api.privatbankApi.CurrencyDTO;
import api.privatbankApi.PrivatApiHelper;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.When;
import pages.PrivatBankMainPage;

import static libs.DriverHelper.getWebDriver;

public class PrivatBank_StepDefinitions {
    private PrivatApiHelper privatApiHelper = new PrivatApiHelper();
    private CurrencyDTO actualCurrencyFromAPI;
    private PrivatBankMainPage privatBankMainPage = new PrivatBankMainPage(getWebDriver());

    @Given("^User gets '(.*)' exchange rate from API$")
    public void user_gets_exchange_rate_from_API(String currencyCode) {
        actualCurrencyFromAPI = privatApiHelper.getCurrencyFromAPIByCode(currencyCode);
    }

    @When("^User opens 'PrivatBank' website$")
    public void user_opens_PrivatBank_website() {
        privatBankMainPage.openPrivatBankPage();
    }

    @When("^User gets '(.*)' exchange rate from 'PrivatBank' website$")
    public void user_gets_exchange_rate_from_PrivatBank_website(){
    }

    @When("^User checks values on 'PrivatBank' website are equal to those received from API$")
    public void user_checks_values_on_PrivatBank_website_are_equal_to_those_received_from_API() {
    }
}
