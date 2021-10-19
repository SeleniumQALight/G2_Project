package StepDefinitions;

import api.ApiHelperPrivatBank;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import libs.TestData;
import org.junit.Assert;
import pages.PrivatBankPage;

public class PrivatBank_StepDefinition {
    private ApiHelperPrivatBank apiHelper_currency = new ApiHelperPrivatBank();
    PrivatBankPage privatBankPage = new PrivatBankPage(DriverHelper.getWebDriver());



    @Given("^User opens 'Privatbank' page$")
    public void userOpensPrivatbankPage() {
        privatBankPage.openPrivatBankPage();
    }

    @Given("^get 'buy' value for '(.*)' via API$")
    public double getCurrencyBuyValueViaAPI(String currencyType) {
        TestData.buyValueGetViaAPI = apiHelper_currency.getCurrencyBuyValueViaAPI(currencyType);
        return TestData.buyValueGetViaAPI;
    }

    @Given("^get 'sale' value for '(.*)' via API$")
    public double getCurrencySaleValueViaAPI(String currencyType) {
        TestData.sellValueGetViaAPI = apiHelper_currency.getCurrencySellValueViaAPI(currencyType);
        return TestData.sellValueGetViaAPI;
    }

    @Given("^get 'buy' value for '(.*)' in 'PrivatBank' website$")
    public double receiveCurrencyBuyValueInUI(String currency) {
        TestData.buyValueInUI = privatBankPage.getCurrencyBuyValueInUI(currency);
        return TestData.buyValueInUI;
    }

    @Given("^get 'sale' value for '(.*)' in 'PrivatBank' website$")
    public double receiveCurrencySaleValueInUI(String currency) {
        TestData.sellValueInUI = privatBankPage.getCurrencySellValueInUI(currency);
        return TestData.sellValueInUI;
    }

    @Then("^compare currency 'buy' values received via API and in 'PrivatBank' website$")
    public void compareBuyValuesReceivedViaAPIAndInUI() {
        Assert.assertTrue(TestData.buyValueInUI == TestData.buyValueGetViaAPI);
    }

    @Then("^compare currency 'sale' values received via API and in 'PrivatBank' website")
    public void compareSaleValuesReceivedViaAPIAndInUI() {
        Assert.assertTrue(TestData.sellValueInUI == TestData.sellValueGetViaAPI);
    }
}
