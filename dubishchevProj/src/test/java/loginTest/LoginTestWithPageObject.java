package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;
import static pages.ParentPage.configProperties;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    @Category(SmokeTestFilter.class)
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }


    @Test
    public void invalidLoginTest() {
        loginPage.fillLoginFormAndSubmit("auto", "123");
        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Warning message is not visible", loginPage.isWarningMessagePresent(), true);
    }

    @Test
    @Parameters({
            "auto,123456qwerty", //valid credentials
            "auto,1", //invalid credentials
            "au,123456qwerty", //invalid credentials
            "auto,123456qwert" //invalid credentials
    })
    @TestCaseName("Invalid login test : login = {0}, password = {1}")
    public void invalidLoginTestWithParameters(String login, String password) {
        loginPage.fillLoginFormAndSubmit(login, password);
        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Warning message is not visible", loginPage.isWarningMessagePresent(), true);
    }

    @Category(SmokeTestFilter.class)
    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(),"validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(dataForValidLogin.get("login"));
        loginPage.enterPasswordInSignIn(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }





}
