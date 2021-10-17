package StepDefinitions;

import api.APIHelper_PrivatBank;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import libs.TestData;
import org.junit.Assert;
import pages.PrivatBankPage;

public class PrivatBank_Definition {
    private APIHelper_PrivatBank apiHelper_currency = new APIHelper_PrivatBank();
    PrivatBankPage privatBankPage = new PrivatBankPage(DriverHelper.getWebDriver());



    @Given("^User opens 'Privatbank' page$")
    public void userOpensPrivatbankPage() {
        privatBankPage.openPrivatBankPage();
    }

    @Given("^get 'buy' value for '(.*)' by API$")
    public double getCurrencyBuyValueByAPI(String currencyType) {
        TestData.buyValueGetByAPI = apiHelper_currency.getCurrencyBuyValueByAPI(currencyType);
        return TestData.buyValueGetByAPI;
    }

    @Given("^get 'sale' value for '(.*)' by API$")
    public double getCurrencySaleValueByAPI(String currencyType) {
        TestData.sellValueGetByAPI = apiHelper_currency.getCurrencySellValueByAPI(currencyType);
        return TestData.sellValueGetByAPI;
    }

    @Given("^get 'buy' value for '(.*)' in 'PrivatBank' website$")
    public double receiveCurrencyBuyValueInUI(String currency) {
        TestData.buyValueInUI = privatBankPage.getCurrencyBuyValueByUI(currency);
        return TestData.buyValueInUI;
    }

    @Given("^get 'sale' value for '(.*)' in 'PrivatBank' website$")
    public double receiveCurrencySaleValueInUI(String currency) {
        TestData.sellValueReceivedByUI = privatBankPage.getCurrencySellValueByUI(currency);
        return TestData.sellValueReceivedByUI;
    }

    @Then("^compare currency 'buy' values received by API and in 'PrivatBank' website$")
    public void compareBuyValuesReceivedByAPIAndInUI() {
        Assert.assertTrue(TestData.buyValueInUI == TestData.buyValueGetByAPI);
    }

    @Then("^compare currency 'sale' values received by API and in 'PrivatBank' website")
    public void compareSaleValuesReceivedByAPIAndInUI() {
        Assert.assertTrue(TestData.sellValueReceivedByUI == TestData.sellValueGetByAPI);
    }
}
