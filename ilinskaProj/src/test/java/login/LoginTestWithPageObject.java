package login;

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

import static pages.ParentPage.configProperties;

@Epic("Allure examples")
@Feature("Junit 4 support")
@Category(SmokeTestFilter.class)
@RunWith(JUnitParamsRunner.class)

public class LoginTestWithPageObject extends BaseTest {
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Category(SmokeTestFilter.class)
@Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginIn(TestData.VALID_LOGIN);
        loginPage.enterPasswwordInSign("123456qwerty");
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible ",homePage.isButtonSignOutPresent(),true);

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
        loginPage.fillinandsubmit(login, password);

        checkExpectedResult("Button SingOut is visible, but shouldn`t",
                homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SingIn is not visible",
                loginPage.isSignINPresent(), true);
        checkExpectedResult("Alert Invalid Sign In login is not visible",
                loginPage.isPopupDisplay(), true);
    }

    @Test
    @Ignore
    @Category(SmokeTestFilter.class)
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginIn(dataForValidLogin.get("login"));
        loginPage.enterPasswwordInSign(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SingOut is not visible",
                homePage.isButtonSignOutPresent(), true);
    }
}



