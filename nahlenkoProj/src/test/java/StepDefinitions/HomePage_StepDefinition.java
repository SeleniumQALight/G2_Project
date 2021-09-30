package StepDefinitions;

import cucumber.api.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinition {
    private LoginPage loginPage = new LoginPage(getWebDriver());
    private HomePage homePage=new HomePage(getWebDriver());
    @Given("^User opens 'Home' page$")
    public void userOpensHomePage() {
        loginPage.loginWithValidCred().checkIsRedirectOnHomePage();
    }

    @When("^User clicks on 'Profile' button on 'Home' page$")
    public void userClickOnProfileButtonOnHomePage() {
        homePage.clickOnButtonProfile();
    }
}
