package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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

    @And("^User sees 'SingOut' button on 'Home' page$")
    public void userSeesSingOutButtonOnHomePage() {
        homePage.checkIsButtonSignOutVisible();
    }

    @And("^User sees 'CreatePost' button on 'Home' page$")
    public void userSeesCreatePostButtonOnHomePage() {
        homePage.checkIsButtonCreatePostVisible();
    }

    @Then("^User does not see 'SingIn' button on 'Home' page$")
    public void userDoesNotSeeSingInButtonOnHomePage() {
        homePage.checkButtonSignInIsNotVisible();
    }
}
