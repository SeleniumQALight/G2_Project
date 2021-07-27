package login;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class Login_InvCrd  extends BaseTest {
@Test
    public void invalidLogin() {

        loginPage.openLoginPage();
        loginPage.enterLoginIn("auto");
        loginPage.enterPasswwordInSign("123");
        loginPage.clickOnButtonSignIn();
        loginPage.isPopupDisplay();
        loginPage.isSignINPresent();
       checkExpectedResult("Sign out present", homePage.isButtonSignOutPresent(), false);

    }
    }
