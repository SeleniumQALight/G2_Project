package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)

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
            "12,qqq",
            "auto,qqq",
            "12,123456qwerty",

    })

    @TestCaseName("invalidLogin: login = {0}, password = {1}")
    public void invalidLogin(String login, String passWord){
        loginPage.fillLoginFormAndSubmit(login, passWord);
        checkExpectedResult("Message 'Invalid username / password' is not visible", homePage.isMessageFailCredentialPresent(), true);
    }
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit(TestData.VALID_LOGIN, "123");
        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SignIn is not visible", homePage.isButtonSignInPresent(), true);
        checkExpectedResult("Message 'Invalid username / password' is not visible", homePage.isMessageFailCredentialPresent(), true);

    }

}

