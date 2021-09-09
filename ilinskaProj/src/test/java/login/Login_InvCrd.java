package login;
import baseTest.BaseTest;
import categories.SmokeTestFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class Login_InvCrd  extends BaseTest {
    @Category(SmokeTestFilter.class)
@Test
    public void invalidLogin() {

       loginPage.fillinandsubmit("auto","123");
       checkExpectedResult("Pop up is not displayed", loginPage.isPopupDisplay(), true);
       checkExpectedResult("Sign in is not displayed", loginPage.isSignINPresent(), true);
       checkExpectedResult("Sign out present", homePage.isButtonSignOutPresent(), false);

    }
    }
