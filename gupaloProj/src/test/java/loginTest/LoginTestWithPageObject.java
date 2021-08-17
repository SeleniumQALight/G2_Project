package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPasswordInSignIn("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button sign out is not visible", homePage.isButtonSignOutPresent(), true);
    }


    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.fillLoginFormAndSubmit("auto", "123");

        checkExpectedResult("Button sign out is not visible", loginPage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button sign in is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Message  'Invalid username / password' is not visible", loginPage.isAlertIsPresent(), true);

    }

    @Test
    @Parameters({

            "auto,1", //invalid credentials
            "au,123456qwerty", //invalid credentials
            "aut,123456qwert" //invalid credentials
    })
    @TestCaseName("Invalid login test : login = {0}, password = {1}")
    public void invalidLoginTestWithParameters(String login, String password) {
        loginPage.fillLoginFormAndSubmit(login, password);
        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Warning message is not visible", loginPage.isWarningMessagePresent(), true);
    }
}
