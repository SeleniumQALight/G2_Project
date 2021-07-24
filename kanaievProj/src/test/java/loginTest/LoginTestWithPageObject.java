package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPasswordInSignIn("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SingOut is not visible",
                homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPasswordInSignIn("123");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SingOut is visible",
                homePage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button SingIn is not visible",
                loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Alert Invalid Sign In login is not visible",
                loginPage.isAlertInvalidSingInPresent(), true);
    }

    @Test
    public void validationOnRegistration(){
        loginPage.openLoginPage();
        loginPage.enterLoginInRegLogin("tr");
        loginPage.enterEmailInRegEmail("test.com");
        loginPage.enterPasswordInRegPassword("123");
        loginPage.clickOnButtonSignUp();

        checkExpectedResult("Alert invalid Sing Up login is not visible",
                loginPage.isAlertInvalidSingUpLoginPresent(), true);
        checkExpectedResult("Alert invalid Sing Up email is not visible",
                loginPage.isAlertInvalidSingUpEmailPresent(), true);
        checkExpectedResult("Alert invalid Sing Up password is not visible",
                loginPage.isAlertInvalidSingUpPasswordPresent(), true);
    }
}
