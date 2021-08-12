package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static libs.TestData.VALID_LOGIN;
import static libs.TestData.VALID_PASSWORD;

@RunWith(JUnitParamsRunner.class)
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
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("logIn");
        loginPage.enterPasswordInSignIn("12345");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button Sign Out is visible, but shouldn't", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button Sign In is visible", homePage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert invalid login present", homePage.isAlertPresent(), true);
    }

    @Test
    @Parameters({
            "login,123456",
            "tryToLogin,098765"
    })
    @TestCaseName("invalid login: SignIn = {0}, Password = {1}")
    public void invalidLoginWithParams(String singIn, String password) {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(singIn);
        loginPage.enterPasswordInSignIn(password);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button Sign Out is visible, but shouldn't", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button Sign In is visible", homePage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert invalid login present", homePage.isAlertPresent(), true);
    }
}
