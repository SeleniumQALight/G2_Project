package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignInForm("auto");
        loginPage.enterPasswordInSignInForm("123456qwerty");
        loginPage.clickOnSignInButton();
        checkExpectedResult("Button SignOut is not displayed", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit("auto", "123");
        checkExpectedResult("Button SignOut is displayed", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SingIn is not displayed", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert message is not displayed", loginPage.isAlertMessagePresent(), true);
    }
}
