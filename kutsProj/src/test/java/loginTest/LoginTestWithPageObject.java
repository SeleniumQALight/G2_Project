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
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPassWordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent() ,true);
    }

    @Test
    @Parameters({ "auto,123", "vs,123456qwerty", "vs,123" })
    @TestCaseName("invalidLogin: login = {0}, password = {1}")
    public void invalidLogin(String login, String password){
        loginPage.openLoginPage();
        loginPage.fillLoginFormAndSubmit(login, password);
        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert Invalid Username / password is not displayed", loginPage.isAlertInvalidUsernamePasswordDisplayed(), true);
    }
}