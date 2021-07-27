package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPassWordInSignIn("12345qwerty");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SingOut is not visible", homePage.isButtonSignOutPresent(), true);
    }
}
