package StepDefinitions;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class HomePage_StepDefinitions {
    private LoginPage loginPage = new LoginPage(getWebDriver());
    private HomePage homePage = new HomePage(getWebDriver());

    @Given("^User opens 'Home' page$" )
    public  void giveUserOpensHomePage(){
        loginPage.loginWithValidCred()
                .checkIsRedirectOnHomePage();
    }


    @When("^User click on 'Profile' button on 'Home' page$")
    public void userClickOnProfileButtonOnHomePage() {
        homePage.clickOnButtonProfile();

    }
}
