package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnbuttonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    @Parameters({
            "user1,123456qwerty",
            "auto,123456"

    })
    @TestCaseName("loginErrors : login = {0}, password = {1}")
    public void inValidLoginOrPassWord(String login, String passWord) {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(login);
        loginPage.enterPasswordInSignIn(passWord);
        loginPage.clickOnbuttonSignIn();
        loginPage.checkErrorMessageForLoginOrPassword();
    }
}
