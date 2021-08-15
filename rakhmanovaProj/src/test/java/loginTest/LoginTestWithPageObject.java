package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LoginTestWithPageObject extends BaseTest {

    @Category(SmokeTestFilter.class)
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }


    @Test
    public void notValidLogin() {
        loginPage.fillLoginFormAndSubmit("o", "123456qwerty");
//        loginPage.openLoginPage();
//        loginPage.enterLoginInSignIn("o");
//        loginPage.enterPasswordInSignIn("123456qwerty");
//        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible as expected", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is present", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert is present", loginPage.isSignInAlertPresent(),true);

    }

}
