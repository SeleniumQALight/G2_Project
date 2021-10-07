package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.TestData;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class LoginPage_StepDefinition {
    private LoginPage loginPage = new LoginPage(getWebDriver());
    final String DEFAULT = "default";

    @Given("^User opens 'Login' page$")
    public void userOpensLoginPage(){
        loginPage.openLoginPage();

    }

    @When("^User enters login '(.*)' into 'Login' input on 'Login' page$")
    public void user_enters_login_into_Login_input_on_Login_page(String userName) throws Throwable {
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN;
        }
        loginPage.enterLoginInSignIn(userName);
    }

    @When("^User enters passWord '(.*)' into 'PassWord' input on 'Login' page$")
    public void user_enters_pass_passWord_into_PassWord_input_on_Login_page(String password) throws Throwable {
        if(DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD;
        }
        loginPage.enterPasswordInSignIn(password);
    }

    @And("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page(){
        loginPage.clickOnButtonSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message) {
       loginPage.checkAlertMessageText(message);
    }

}
