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

    @Test
    @Parameters({
            "12,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "12,qqq,123456789012,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("validationLoginAndEmailOnRegistration : login = {0}, email = {1}, password = {2}")
    public void validationLoginAndEmailOnRegistration(String login, String email, String password, String errors){
        loginPage.openLoginPage();
        loginPage.enterLoginInRegLogin(login);
        loginPage.enterEmailInRegEmail(email);
        loginPage.enterPasswordInRegPassword(password);
        loginPage.clickOnButtonSignUp();

        loginPage.checkErrors(errors);
    }
}
