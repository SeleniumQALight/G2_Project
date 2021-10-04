package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;
import static libs.DriverHelper.webDriver;

public class HomePage_StepDefinition {
    private LoginPage loginPage = new LoginPage(getWebDriver());
    private HomePage homePage= new HomePage(getWebDriver());
   @Given ("^User opens 'Home' page$")
    public void givenUserOpensHomePage(){
       loginPage.loginWithValidCred().checkIsButtonSignOutVisible();

   }
    @When("^User clicks on 'Profile' button  on 'Home' page$")
    public void userClickonProfileButtonHomePage(){
       homePage.clickOnProfileButton();


    }

}