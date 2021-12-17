package StepDefinitionsPrivateBank;

import apiPrivateBank.ApiHelper;
import apiPrivateBank.CurrencyDTO;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.TestData;
import org.junit.Assert;
import pages.PrivateBankHomePage;

import static libs.DriverHelper.getWebDriver;

public class PrivateBank_API_StepDefinition {
    private ApiHelper apiHelper = new ApiHelper();
    private PrivateBankHomePage uiHomePage = new PrivateBankHomePage(getWebDriver());
    private CurrencyDTO[] apiCurrencies;
    private CurrencyDTO apiTestCurrency;

    @Given("Open PrivateBank API HomePage")
    public void givenOpenPrivateBankAPIHomePage(){
        apiCurrencies = apiHelper.getApiCourseRequest(5);
    }
    @When("^Remember Exchange Course '(.*)' and '(.*)' from API$")
    public void whenRememberApiExchangeCourse(String testCcy, String testBaseCcy){
        apiTestCurrency = apiHelper.extractCurrency(apiCurrencies, testCcy, testBaseCcy);
        Assert.assertNotNull("Currency pair wasn't found ", apiTestCurrency);
    }
    @Given("Open PrivateBank UI HomePage")
    public void givenOpenPrivateBankUIHomePage() {
        uiHomePage.openPage();
    }

    @When("^Remember Exchange Course '(.*)' and '(.*)' from UI$")
    public void whenRememberUiExchangeCourse(String testCcy, String testBaseCcy) {
        String ccy = testCcy;
        if(!testBaseCcy.equals("UAH")){
            ccy = ccy + "/" + testBaseCcy;
        }
        Assert.assertTrue("Currency was not found in UI ", uiHomePage.getExchangeCourse(ccy));
    }

    @Then("Compare Course UI and API Course")
    public void compareCourses(){
        String buyValue = apiTestCurrency.getBuy();
        String uiBuy = uiHomePage.buyValue;
        Assert.assertNotEquals("Api Currency buy is not the same as UI ", buyValue, uiBuy);

        String saleValue = apiTestCurrency.getSale();
        String uiSale = uiHomePage.saleValue;
        Assert.assertNotEquals("Api Currency sale is not the same as UI ", saleValue, uiSale);
    }

    @Given("^Exchange Course '(.*)' and '(.*)' API call$")
    public void givenExchangeCourseCall(String testCcy, String testBaseCcy) {
        CurrencyDTO[] currencies = apiHelper.getApiCourseRequest(5);
        CurrencyDTO testCurrency = apiHelper.extractCurrency(currencies, testCcy, testBaseCcy);

        Assert.assertNotNull("Currency pair wasn't found ", testCurrency);
        apiHelper.compareUiCourse(testCcy, testBaseCcy, testCurrency.getBuy(), testCurrency.getSale());

    }
}