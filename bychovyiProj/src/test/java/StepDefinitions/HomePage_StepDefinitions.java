package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;


import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinitions {
    private HomePage homePage = new HomePage(getWebDriver());
    private LoginPage loginPage = new LoginPage(getWebDriver());

    @Then("^User redirect on 'Home' page$")
    public void user_redirect_on_Home_page() {
        homePage.checkIsRedirectOnHomePage();
    }

    @Given("^User opens 'Home' page$")
    public void givenUserOpensHomePage() {
        loginPage.loginWithValidCred()
                .checkIsRedirectOnHomePage();
    }

    @When("^User clicks on 'Profile' button on 'Home' page$")
    public void userClicksOnProfileButtonOnHomePage() {
        homePage.clickOnButtonProfile();
    }
}
