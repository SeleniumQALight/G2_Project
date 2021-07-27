package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN;
import static libs.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {


    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(VALID_LOGIN);
        loginPage.enterPasswordInSignIn(VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button Sign Out isn't visible", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void invalidLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(VALID_LOGIN);
        loginPage.enterPasswordInSignIn(VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button Sign Out is visible, but shouldn't", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button Sign In is visible", homePage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert invalid login present", homePage.isAlertPresent(), true);
    }
}
