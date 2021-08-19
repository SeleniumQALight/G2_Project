package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import pages.ParentPage;

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

    @Category(SmokeTestFilter.class)
    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(dataForValidLogin.get("login"));
        loginPage.enterPasswordInSignIn(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void notValidLogin() {
        loginPage.fillLoginFormAndSubmit("o", "123456qwerty");
//        loginPage.openLoginPage();
//        loginPage.enterLoginInSignIn("o");
//        loginPage.enterPasswordInSignIn("123456qwerty");
//        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible as expected", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is present", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert is present", loginPage.isSignInAlertPresent(),true);

    }

    @Test
    @Parameters({
            "lj, jg@#!",
            "0, 123456qwerty",
            "77, 09876param"
    })
    @TestCaseName("login = {0}, password ={1}")
    public void notValidLoginS(String login, String password) {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignInParam(login);
        loginPage.enterPasswordInSignInS(password);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible as expected", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is present", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert is present", loginPage.isSignInAlertPresent(),true);

    }
}
