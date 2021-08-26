package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.ParentPage;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObject extends BaseTest {
    @Category(SmokeTestFilter.class)
    @Test
    public void validLogin(){
    loginPage.openLoginPage();
    loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
    loginPage.enterPasswordInSignIn(TestData.VALID_PASS);
    loginPage.clickOnButtonSignIn();

    checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent() ,true);
    }

    @Test
    public void validLogin1(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignIn(TestData.VALID_PASS);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent() ,true);
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(dataForValidLogin.get("login"));
        loginPage.enterPasswordInSignIn(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent() ,true);
    }



}
