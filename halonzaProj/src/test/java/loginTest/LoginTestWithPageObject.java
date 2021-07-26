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
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.fillLoginFormAndSubmit("auto", "123");
        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(),false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(),true);
        checkExpectedResult("SignIn alert is not visible", loginPage.isSignInAlertPresent(),true);
    }

    @Test
    public void signUpFieldValidationAlerts(){
        loginPage.openLoginPage();
        loginPage.fillSignUpFormAndSubmit("tr", "test.com", "123");
        checkExpectedResult("Invalid username alert is not visible", loginPage.isUsernameSignUpAlertPresent(), true);
        checkExpectedResult("Invalid email alert is not visible", loginPage.isEmailSignUpAlertPresent(),true);
        checkExpectedResult("Invalid password alert is not visible", loginPage.isPasswordSignUpAlertPresent(),true);
    }
}
