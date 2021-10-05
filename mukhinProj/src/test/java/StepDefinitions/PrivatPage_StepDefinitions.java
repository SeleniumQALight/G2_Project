package StepDefinitions;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class PrivatPage_StepDefinitions {
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


    @Given("^User opens 'Login' page$")
    public  void userOpensLoginPage(){
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_Wrong_login_login_into_Login_input_on_Login_page(String userName) {
        loginPage.enterLoginInSignIn(userName);

    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_Wrong_pass_passWord_into_PassWord_input_on_Login_page(String passWord) {
        loginPage.enterPassWordInSignIn(passWord);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
        loginPage.clickOnButtonSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message) {
        loginPage.checkAlertMessageText(message);
    }

    @Given("User opens 'Main' page")
    public void userOpensMainPage() {
        loginPage.openPrivatPage();
    }
}
