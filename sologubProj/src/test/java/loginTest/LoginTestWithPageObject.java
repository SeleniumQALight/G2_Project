package loginTest;

import baseTest.BaseTest;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.categories.Category;
import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Test
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignIn(TestData.VALID_PASSWORD);
       // loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(dataForValidLogin.get("login"));
        loginPage.enterPasswordInSignIn(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }

    @Test
    @Ignore
    @Parameters ({
            "aut, 123456qwerty",
            "auto, 123456qwert",
            "auto1, 123456qwerty1",
            " , "
    })
   @TestCaseName("Login with invalid credentials: login = {0}, password = {1}")
    public void invalidLogin(String login, String passord) {
        loginPage.fillLoginFormAndSubmit(login, passord);
        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button Sign In is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert about invalid credentialsPresent", loginPage.isInvalidCredentialsAlertPresent(), true);
    }
}
