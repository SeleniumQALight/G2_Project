package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignInForm("auto");
        loginPage.enterPasswordInSignInForm("123456qwerty");
        loginPage.clickOnSignInButton();
        checkExpectedResult("Button SignOut is not displayed", homePage.isButtonSignOutPresent(), true);
    }
}
