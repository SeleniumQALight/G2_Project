package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPassWordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SingOut is not visible", homePage.isButtonSignOutPresent(), true);
    }
}
