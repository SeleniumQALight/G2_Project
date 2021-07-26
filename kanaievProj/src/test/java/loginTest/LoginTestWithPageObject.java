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
