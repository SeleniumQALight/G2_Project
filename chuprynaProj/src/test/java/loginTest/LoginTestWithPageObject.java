package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignInForm(TestData.VALID_LOGIN);
        loginPage.enterPasswordInSignInForm(TestData.VALID_PASSWORD);
        loginPage.clickOnSignInButton();
        checkExpectedResult("Button SignOut is not displayed", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit("auto", "123");
        checkExpectedResult("Button SignOut is displayed", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SingIn is not displayed", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert message is not displayed", loginPage.isAlertMessagePresent(), true);
    }
}
