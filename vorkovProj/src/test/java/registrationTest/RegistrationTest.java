package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTest extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void errorMessagesInRegistrationForm() {
        loginPage.openLoginPage();
        loginPage.enterUsernameRegistration("tr");
        loginPage.enterEmailRegistration("test.com");
        loginPage.enterPasswordRegistration("123");
        loginPage.clickOnButtonSignUpRegistration();

        checkExpectedResult("Validation message for input 'Username' isn't visible", homePage.isValidationMessageUsernameRegistrationPresent(), true);
        checkExpectedResult("Validation message for input 'Email' isn't visible", homePage.isValidationMessageEmailRegistrationPresent(), true);
        checkExpectedResult("Validation message for input 'Password' isn't visible", homePage.isValidationMessagePasswordRegistrationPresent(), true);
    }

    @Test
    @Parameters({
            "tr,test.com,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "tr,test.com,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors: Login = {0}, Email = {1}, Password = {2}")
    public void registrationErrors(String login, String email, String password, String errors){
        loginPage.openLoginPage();
        loginPage.enterUsernameRegistration(login);
        loginPage.enterEmailRegistration(email);
        loginPage.enterPasswordRegistration(password);
        loginPage.checkErrorsMessages(errors);
    }
}
