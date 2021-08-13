package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    
    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SingOut is not visible",
                homePage.isButtonSignOutPresent(), true);
    }

    @Test
    @Parameters({
            "auto,123",
            "auto,",
            ",556677",
            ","
    })
    @TestCaseName("invalidLogin with login = \"{0}\" and password = \"{1}\"")
    public void invalidLogin(String login, String password) {
        loginPage.fillLoginFormAndSubmit(login, password);

        checkExpectedResult("Button SingOut is visible, but shouldn`t",
                homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SingIn is not visible",
                loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert Invalid Sign In login is not visible",
                loginPage.isAlertInvalidSingInPresent(), true);
    }
}
