package StepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class LoginPage_StepDefinition {
    private LoginPage loginPage=new LoginPage(getWebDriver());

    @Given("^User opens 'Login' page$")
    public void userOpensLoginPage(){
        loginPage.openLoginPage();

    }
    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_Wrong_login_login_into_Login_input_on_Login_page(String userName)  {
        // Write code here that turns the phrase above into concrete actions
        loginPage.enterLoginInSignIn(userName);
    }
    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_Wrong_pass_passWord_into_PassWord_input_on_Login_page(String passWord)  {
        // Write code here that turns the phrase above into concrete actions
        loginPage.clickOnButtonSignIn();
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page()  {
        // Write code here that turns the phrase above into concrete actions
        loginPage.clickOnButtonSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message)  {
        // Write code here that turns the phrase above into concrete actions
        loginPage.checkAlertMessageText(message);
    }

}
