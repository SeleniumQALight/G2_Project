package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PBLandingPage;

import static libs.DriverHelper.getWebDriver;

public class PBLandingPage_StepDefinition {
    private PBLandingPage landingPage = new PBLandingPage(getWebDriver());

    @When("^User opens PrivatBank Lending Page$")
    public void userOpensPrivatBankLendingPage() {
        landingPage.openLoginPage();
    }

    @Then("^User gets '(.*)' course from Lending Page and compare it with API data$")
    public void userGetsCurrencyCourseFromLendingPageAndCompareItWithAPIData(String currencyCode) {
        landingPage.getCurrencyCourseFromLendingPageAndCompareItWithAPIData(currencyCode);
    }
}
