package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;


@RunWith(JUnitParamsRunner.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    
    @Test
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN+"52234");
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

    @Test
    @Ignore
    @Category(SmokeTestFilter.class)
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(dataForValidLogin.get("login"));
        loginPage.enterPasswordInSignIn(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SingOut is not visible",
                homePage.isButtonSignOutPresent(), true);
    }
}
