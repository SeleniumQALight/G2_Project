package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SingOut is not visible",
                homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void invalidLogin() {
        loginPage.fillLoginFormAndSubmit("auto", "123");

        checkExpectedResult("Button SingOut is visible, but shouldn`t",
                homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SingIn is not visible",
                loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert Invalid Sign In login is not visible",
                loginPage.isAlertInvalidSingInPresent(), true);
    }
}
