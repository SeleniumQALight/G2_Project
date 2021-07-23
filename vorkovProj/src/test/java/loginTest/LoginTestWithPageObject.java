package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {


    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPasswordInSignIn("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button Sign Out isn't visible", homePage.isButtonSignOutPresent(), true);


    }
}
