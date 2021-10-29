package StepDefinitions;

import api.ApiHelperPrivat;
import cucumber.api.java.en.Given;


import static libs.TestData.currencyDTOFromAPI;

public class PrivatBankAPI_StepDefinition {

    private ApiHelperPrivat apiHelperPrivat=new ApiHelperPrivat();

    @Given("^User gets '(.*)' course on ApiPrivatTest page$")
    public void userGetsCurrencyCourseOnApiPrivatTestPage(String currency) {

        currencyDTOFromAPI=apiHelperPrivat.getCurrencyDTO(currency);
    }
}
