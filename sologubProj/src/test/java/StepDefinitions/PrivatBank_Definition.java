package StepDefinitions;

import api.APIHelper_PrivatBank;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import org.junit.Assert;
import pages.PrivatBankPage;

public class PrivatBank_Definition {
    private APIHelper_PrivatBank apiHelper_currency = new APIHelper_PrivatBank();
    PrivatBankPage privatBankPage = new PrivatBankPage(DriverHelper.getWebDriver());

    public double buyValueGetByAPI;
    public double sellValueGetByAPI;
    public double buyValueInUI;
    public double sellValueReceivedByUI;

    @Given("^User opens 'Privatbank' page$")
    public void userOpensPrivatbankPage() {
        privatBankPage.openPrivatBankPage();
    }

    @Given("^get 'buy' value for '(.*)' by API$")
    public double getCurrencyBuyValueByAPI(String currencyType) {
        buyValueGetByAPI = apiHelper_currency.getCurrencyBuyValueByAPI(currencyType);
        return buyValueGetByAPI;
    }

    @Given("^get 'sale' value for '(.*)' by API$")
    public double getCurrencySaleValueByAPI(String currencyType) {
        sellValueGetByAPI = apiHelper_currency.getCurrencySellValueByAPI(currencyType);
        return sellValueGetByAPI;
    }

    @Given("^get 'buy' value for '(.*)' in 'PrivatBank' website$")
    public double receiveCurrencyBuyValueInUI(String currency) {
        buyValueInUI = privatBankPage.getCurrencyBuyValueByUI(currency);
        return buyValueInUI;
    }

    @Given("^get 'sale' value for '(.*)' in 'PrivatBank' website$")
    public double receiveCurrencySaleValueInUI(String currency) {
        sellValueReceivedByUI = privatBankPage.getCurrencySellValueByUI(currency);
        return sellValueReceivedByUI;
    }

    @Then("^compare currency 'buy' values received by API and in 'PrivatBank' website$")
    public void compareBuyValuesReceivedByAPIAndInUI() {
        Assert.assertTrue(buyValueInUI == buyValueGetByAPI);
    }

    @Then("^compare currency 'sale' values received by API and in 'PrivatBank' website")
    public void compareSaleValuesReceivedByAPIAndInUI() {
        Assert.assertTrue(sellValueReceivedByUI == sellValueGetByAPI);
    }
}
