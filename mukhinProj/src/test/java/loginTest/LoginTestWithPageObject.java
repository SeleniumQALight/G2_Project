package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
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
    @Parameters({
            "12,qqq,Invalid username / password",
            "auto,qqq,Invalid username / password",
            "12,123456qwerty,Invalid username / password",

    })

    @TestCaseName("loginErrors: login = {0}, password = {1}")
    public void invalidLogin(String login, String passWord){
        loginPage.fillLoginFormAndSubmit(login, passWord);
        checkExpectedResult("Message 'Invalid username / password' is not visible", homePage.isMessageFailCredentialPresent(), true);

        ;
    }
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit(TestData.VALID_LOGIN, "123");
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is not visible", homePage.isButtonSignInPresent(), true);
        checkExpectedResult("Message 'Invalid username / password' is not visible", homePage.isMessageFailCredentialPresent(), true);

    }

}

