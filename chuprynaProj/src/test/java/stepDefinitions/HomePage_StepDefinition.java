package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinition {
    private LoginPage loginPage = new LoginPage(getWebDriver());
    private HomePage homePage = new HomePage(getWebDriver());

    @Given("^User opens 'Home' page$")
    public void givenUserOpensHomePage() {
        loginPage.loginWithValidCredentials()
                .checkIsRedirectedOnHomePage();
    }

    @And("^User click on 'Profile' button on 'Home' page$")
    public void userClickOnProfileButtonOnHomePage() {
        homePage.clickOnButtonProfile();
    }

    @Then("^User is redirected to 'Home' page$")
    public void user_is_redirected_to_Home_page() {
        homePage.checkIsRedirectedOnHomePage();
    }

}
