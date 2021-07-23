package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {


    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPasswordInSignIn("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button Sign Out isn't visible", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPasswordInSignIn("123");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button Sign Out is visible, but shouldn't", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button Sign In is visible", homePage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert invalid login present", homePage.isAlertPresent(), true);

    }
}
