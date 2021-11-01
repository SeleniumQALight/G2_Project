package StepDefinitionsPrivateBank;

import apiPrivateBank.ApiHelper;
import apiPrivateBank.CurrencyDTO;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import libs.TestData;
import org.junit.Assert;

public class PrivateBank_API_StepDefinition {
    private ApiHelper apiHelper = new ApiHelper();

    @Given("^Exchange Course '(.*)' and '(.*)' API call$")
    public void givenExchangeCourseCall(String testCcy, String testBaseCcy) {
        CurrencyDTO[] currencies = apiHelper.getApiCourseRequest(5);
        CurrencyDTO testCurrency = apiHelper.extractCurrency(currencies, testCcy, testBaseCcy);

        Assert.assertNotNull("Currency pair wasn't found ", testCurrency);
        apiHelper.compareUiCourse(testCcy, testBaseCcy, testCurrency.getBuy(), testCurrency.getSale());

    }
}