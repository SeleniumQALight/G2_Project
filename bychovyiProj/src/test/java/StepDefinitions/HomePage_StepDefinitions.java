package StepDefinitions;

<<<<<<< HEAD
import cucumber.api.java.en.Then;
import pages.HomePage;
=======
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;
>>>>>>> 3c469c28421e914b532ccae11c9a5af1ffa44e56

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinitions {
<<<<<<< HEAD
    private HomePage homePage = new HomePage(getWebDriver());

    @Then("^User redirect on 'Home' page$")
    public void user_redirect_on_Home_page() {
        homePage.checkIsRedirectOnHomePage();
=======
    private LoginPage loginPage = new LoginPage(getWebDriver());
    private HomePage homePage = new HomePage(getWebDriver());

    @Given("^User opens 'Home' page$")
    public void givenUserOpensHomePage() {
        loginPage.loginWithValidCred()
                .checkIsRedirectOnHomePage();
    }

    @When("^User clicks on 'Profile' button on 'Home' page$")
    public void userClicksOnProfileButtonOnHomePage() {
        homePage.clickOnButtonProfile();
>>>>>>> 3c469c28421e914b532ccae11c9a5af1ffa44e56
    }
}
