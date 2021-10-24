package StepsDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class HomePage_StepDefinition {
    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());

    public LoginPage loginPage = new LoginPage((DriverHelper.getWebDriver()));

    @Given("^User opens 'Home' page$")
    public void givenUserOpensHomePage() {
        loginPage.loginWithValidCread().checkIsRedirectOnHomePage();


    }
    @When("^User clicks on 'Profile' button on 'Home page$")
    public void userClicksOnProfileButtonOnHomePage(){
        homePage.clickOnButtonProfile();

    }
}
