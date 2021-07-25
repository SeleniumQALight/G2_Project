package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPassWordInSignIn("123456qwerty");
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent() ,true);
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.fillLoginFormAndSubmit("auto", "123");
        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert Invalid Username / password is not displayed", loginPage.isAlertInvalidUsernamePasswordDisplayed(), true);
    }

    @Test
    public void registrationAlerts(){
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration("tr");
        loginPage.enterEmailRegistration("test.com");
        loginPage.enterPassWordInRegistration("123");
        loginPage.clickOnButtonSignUp();
        checkExpectedResult("Alert Invalid Username Registration is not displayed", loginPage.isAlertInvalidUsernameRegistrationDisplayed(), true);
        checkExpectedResult("Alert Invalid Email Registration is not displayed", loginPage.isAlertInvalidEmailRegistrationDisplayed(), true);
        checkExpectedResult("Alert Invalid Password Registration is not displayed", loginPage.isAlertInvalidPasswordRegistrationDisplayed(), true);
    }
}