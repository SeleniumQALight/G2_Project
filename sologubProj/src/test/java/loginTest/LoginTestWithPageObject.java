package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignIn(TestData.VALID_LOGIN);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }

    @Test
    public void invalidLogin() {
        loginPage.fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_LOGIN);
        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button Sign In is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert about invalid credentialsPresent", loginPage.isInvalidCredentialsAlertPresent(), true);
    }
}
