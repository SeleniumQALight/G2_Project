package loginTest;

import baseTest.BaseTest;

import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import pages.ParentPage;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    public static final String[] invalidLoginData = {"auto:123", "auto2:123", "auto3:789"};

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPassWordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }

    @Test
    @Category(SmokeTestFilter.class)
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(dataForValidLogin.get("login"));
        loginPage.enterPassWordInSignIn(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }
    @Test
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit("auto", "123");

//        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(),false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(),true);
        checkExpectedResult("label Invalid Login is not present", loginPage.isLabelMessageInvalidLoginPresent(),true);
    }
    @Test
    @Parameters({"auto,123", "auto2,123", "auto3,789"})
    public void paramInvalidLogin(String login, String password){

            loginPage.fillLoginFormAndSubmit(login, password);

            checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(), true);
            checkExpectedResult("label Invalid Login is not present", loginPage.isLabelMessageInvalidLoginPresent(), true);
    }
//    @Test
//    public void registrationValidationMessages(){
//        loginPage.fillRegistrationFormAndSubmit("tr", "test.com", "123");
//
//        checkExpectedResult("Label short Username is not present", loginPage.isLabelMessageShortUsernamePresent(),true);
//        checkExpectedResult("Label valid email is not present", loginPage.isLabelMessageValidEmailPresent(),true);
//        checkExpectedResult("Label password is not 12 characters", loginPage.isLabelMessagePasswordPresent(),true);
//    }
}
