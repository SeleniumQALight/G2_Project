package loginTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPassWordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit(TestData.VALID_LOGIN, "123");
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is not visible", homePage.isButtonSignInPresent(), true);
        checkExpectedResult("Message 'Invalid username / password' is not visible", homePage.isMessageFailCredentialPresent(), true);

    }

}

