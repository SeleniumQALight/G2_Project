package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
}
