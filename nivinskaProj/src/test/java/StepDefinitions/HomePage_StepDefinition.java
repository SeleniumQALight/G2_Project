package StepDefinitions;

import cucumber.api.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinition {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    @Given("^User opens 'Home' page$")
    public void givenUserOpensHomePage() {
        loginPage.loginWithValidCred().checkIsRedirectOnHomePage();
    }

    @When("^User clicks on 'Profile' button on 'Home' page$")
    public void userClicksOnProfileButtonOnHomePage() {
        homePage.clickOnButtonProfile();
    }
}
