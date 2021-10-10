package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.TestData;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class LoginPage_StepDefinitions {
    private  LoginPage loginPage = new LoginPage(getWebDriver());
    final  String DEFAULT = "default";

    @Given("User opens 'Login' page$")
    public void UserOpensLoginPage() {
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

    @When("^User enters valid '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_valid_Default_Login_Into_Login_input_on_Login_page (String UserName){
        loginPage.enterLoginInSignIn(UserName);
        if (DEFAULT.equalsIgnoreCase(UserName)){
            UserName = TestData.VALID_LOGIN;
        }
    }
    @And("^User enters valid '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public  void user_enters_valid_Default_passWord_into_PassWord_input_on_Login_page (String Password){
        loginPage.enterPassWordInSignIn(Password);
        if (DEFAULT.equalsIgnoreCase(Password))
            Password = TestData.VALID_PASSWORD;
        }
    @Then("^User sees button Sign Out$")
    public  void user_sees_button_Sign_Out (String message){
    loginPage.checkButtonSignOut(message);
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message)  {
     loginPage.checkAlertMessageText(message);
    }

        }


