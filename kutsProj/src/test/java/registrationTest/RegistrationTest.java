package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class RegistrationTest extends BaseTest {
    @Test
    public void registrationAlerts(){
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration("tr");
        loginPage.enterEmailInRegistration("test.com");
        loginPage.enterPassWordInRegistration("123");
        loginPage.clickOnButtonSignUp();
        checkExpectedResult("Alert Invalid Username Registration is not displayed", loginPage.isAlertInvalidUsernameRegistrationDisplayed(), true);
        checkExpectedResult("Alert Invalid Email Registration is not displayed", loginPage.isAlertInvalidEmailRegistrationDisplayed(), true);
        checkExpectedResult("Alert Invalid Password Registration is not displayed", loginPage.isAlertInvalidPasswordRegistrationDisplayed(), true);
    }

    @Test
    public void validationRegistration() {
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration("12");
        loginPage.enterEmailInRegistration("tests.ua");
        loginPage.enterPassWordInRegistration("0123456789123");
        loginPage.clickOnButtonSignUp();

        checkExpectedResult("Expected alerts are not displayed",
                loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address."),
                true);
    }

    @Test
    @Parameters({
            "12,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "12,qqq,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String passWord, String errors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegistration(login)
                .enterEmailInRegistration(email)
                .enterPassWordInRegistration(passWord)
                .checkErrorsMessages(errors)
        ;
    }
}
