package loginTest;

import org.junit.Test;

import baseTest.BaseTest;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPassWordInSignIn("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent() , true);
    }
}
