package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
    loginPage.openLoginPage();
    loginPage.enterLoginInSignIn("auto");
    loginPage.enterPasswordInSignIn("123456qwerty");
    loginPage.clickOnButtonSignIn();

    checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignInPresent() ,true);
    }

}
