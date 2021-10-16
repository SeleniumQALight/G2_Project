package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class ValidLoginPage_StepDefinitions {
    private  LoginPage loginPage = new LoginPage(getWebDriver());

    @Given("User opens 'Login' page$")
    public void UserOpensLoginPage() {
        loginPage.openLoginPage();
    }
    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_valid_login_login_into_Login_input_on_Login_page(String userName) {
        loginPage.enterLoginInSignIn(userName);

        }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_valid_pass_passWord_into_PassWord_input_on_Login_page(String passWord) {
        loginPage.enterPassWordInSignIn(passWord);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() {
        loginPage.clickOnButtonSignIn();
    }

    @Then("^User sees user name$")
    public void user_sees_user_name(String message)  {
        loginPage.checkUserName(message);

    }

        }


