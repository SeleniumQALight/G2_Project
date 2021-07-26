package login;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Login_InvCrd  extends BaseTest {
@Test
    public void invalidLogin() {

        loginPage.openLoginPage();
        loginPage.enterLoginIn("auto");
        loginPage.enterPasswwordInSign("123");
        loginPage.clickOnButtonSignIn();
        loginPage.isPopupDisplay("Error Sign in Message displays on the screen");
        homePage.isSignINPresent();
        homePage.isButtonSignOutPresent();



    }
    }
