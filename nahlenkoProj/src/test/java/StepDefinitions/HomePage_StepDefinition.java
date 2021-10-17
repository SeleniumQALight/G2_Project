package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinition {
    public LoginPage loginPage = new LoginPage(getWebDriver());
    public HomePage homePage=new HomePage(getWebDriver());

    @Given("^User opens 'Home' page$")
    public void user_opens_Home_page() {
        loginPage.loginWithValidCred().checkIsRedirectOnHomePage();

    }

    @When("^User clicks on 'Profile' button on 'Home' page$")
    public void user_clicks_on_Profile_button_on_Home_page() {
        homePage.clickOnButtonProfile();
    }
}
