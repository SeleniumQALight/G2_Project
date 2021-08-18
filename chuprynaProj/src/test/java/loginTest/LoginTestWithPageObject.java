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

@Category(SmokeTestFilter.class)
@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignInForm(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignInForm(TestData.VALID_PASSWORD);
        loginPage.clickOnSignInButton();
        checkExpectedResult("Button SignOut is not displayed", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    @Parameters({
            "auto,123",
            "au,123456qwerty",
            ",123",
            ","
    })
    @TestCaseName("Invalid Log In for: username = {0}, password = {1}")
    public void invalidLogin(String userName, String passWord){
        loginPage.fillLoginFormAndSubmit(userName, passWord);
        checkExpectedResult("Button SignOut is displayed", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SingIn is not displayed", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert message is not displayed", loginPage.isAlertMessagePresent(), true);
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginInSignInForm(dataForValidLogin.get("login"));
        loginPage.enterPasswordInSignInForm(dataForValidLogin.get("pass"));
        loginPage.clickOnSignInButton();
        checkExpectedResult("Button SignOut is not displayed", homePage.isButtonSignOutPresent(), true);
    }
}
