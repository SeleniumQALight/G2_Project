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
        loginPage.fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.INVALID_PASSWORD);
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is not visible", homePage.isButtonSignInPresent(), true);
        checkExpectedResult("Message 'Invalid username / password' is not visible", homePage.isMessageFailCredentialPresent(), true);

    }

    @Test
    public void checkValidationMessageInFormRegistration(){
        loginPage.fillSignOutFormAndSubmit(TestData.INVALID_USERNAME, TestData.INVALID_EMAIL, TestData.INVALID_PASSWORD);
        checkExpectedResult("Message 'Username must be at least 3 characters' is not visible", homePage.isMessageFailUserNamePresent(), true);
        checkExpectedResult("Message 'You must provide a valid email address.' is not visible", homePage.isMessageFailEmailPresent(), true);
        checkExpectedResult("Message 'Password must be at least 12 characters.' is not visible", homePage.isMessageFailPassWordPresent(), true);

    }
}

