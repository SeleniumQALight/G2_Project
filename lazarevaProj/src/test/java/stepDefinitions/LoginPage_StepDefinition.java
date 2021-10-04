package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class LoginPage_StepDefinition {

    private LoginPage loginPage = new LoginPage(getWebDriver());
    private HomePage homePage = new HomePage(getWebDriver());

    @Given("^User opens 'Login' page$")
    public void userOpenLoginPage() {
        loginPage.openLoginPage();
    }

    //User enters correct or wrong credentials
    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void userEntersLoginIntoLoginInputOnLoginPage(String userName) {
        loginPage.enterLoginInSignIn(userName);

    }
    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void userEnterPassWordIntoPassWordInputOnLoginPage(String passWord) {
       loginPage.enterPassWordInSignIn(passWord);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page()  {
        loginPage.clickOnButtonSignIn();
    }
    // Verify the Alert message in case - user entered wrong credentials
    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String message) {
        loginPage.checkAlertMessageText(message);

    }
    // Verify if the SignOut BTN is visible in case - user entered wrong credentials
    @Then("^User sees 'Sign Out' button on 'Home' page$")
    public  void  userSeesSignOutButtonOnHomePage () {
        homePage.checkIsButtonSignOutVisible();

    }

}
