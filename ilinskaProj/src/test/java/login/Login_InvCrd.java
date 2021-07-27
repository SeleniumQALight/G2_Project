package login;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class Login_InvCrd  extends BaseTest {
@Test
    public void invalidLogin() {

       loginPage.fillinandsubmit("auto","123");
       checkExpectedResult("Pop up is not displayed", loginPage.isPopupDisplay(), true);
       checkExpectedResult("Sign in is not displayed", loginPage.isSignINPresent(), true);
       checkExpectedResult("Sign out present", homePage.isButtonSignOutPresent(), false);

    }
    }
