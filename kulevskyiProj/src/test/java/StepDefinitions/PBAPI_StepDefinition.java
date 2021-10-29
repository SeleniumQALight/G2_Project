package StepDefinitions;

import api.PBApiHelper;
import cucumber.api.java.en.Given;
import libs.TestData;

public class PBAPI_StepDefinition {
    private PBApiHelper pbApiHelper = new PBApiHelper();

    @Given("^User gets '(.*)' course from PrivatBank API$")
    public void userGetsCurrencyCourseFromPrivatBankAPI(String currencyCode) {
        TestData.currencyDTOFromAPI = pbApiHelper.getPBCurrencyDTOFromApiByCurrencyCode(currencyCode);
    }
}
