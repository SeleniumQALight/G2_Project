package StepDefinitions;

import cucumber.api.java.en.Then;
import pages.HomePage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinition {
    private HomePage homePage = new HomePage(getWebDriver());

    @Then("^User is redirected to 'Home' page$")
    public void user_is_redirected_to_Home_page(){
        homePage.checkIsRedirectOnHomePage();
    }
}
