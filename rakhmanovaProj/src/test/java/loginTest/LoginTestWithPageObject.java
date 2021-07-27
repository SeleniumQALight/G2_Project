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


        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }


    @Test
    public void notValidLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("o");
        loginPage.enterPasswordInSignIn("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible as expected", homePage.isButtonSignOutPresent(), false);
       // checkExpectedResult("Button SignIn is present", homePage.is);


    }


}
