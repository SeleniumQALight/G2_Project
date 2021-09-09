package login;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

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
        loginPage.enterLoginIn(TestData.VALIG_LOGin);
        loginPage.enterPasswwordInSign("123456qwerty");
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible ",homePage.isButtonSignOutPresent(),true);

    }



}
