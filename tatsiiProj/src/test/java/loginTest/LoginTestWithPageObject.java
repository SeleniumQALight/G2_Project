package loginTest;

import baseTest.BaseTest;

import categories.SmokeTestFilter;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;

public class LoginTestWithPageObject extends BaseTest {

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
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit("auto", "123");

        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(),false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(),true);
        checkExpectedResult("label Invalid Login is not present", loginPage.isLabelMessageInvalidLoginPresent(),true);
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
