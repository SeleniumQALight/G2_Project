package StepDefinitions;

import cucumber.api.java.en.Then;
import pages.HomePage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinitions {
    private HomePage homePage = new HomePage(getWebDriver());

    @Then("^User redirect on 'Home' page$")
    public void user_redirect_on_Home_page() {
        homePage.checkIsRedirectOnHomePage();
    }
}
