package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
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
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage.enterUsernameRegistration("tr");
        loginPage.enterEmailRegistration("test.com");
//        loginPage.enterPasswordRegistration("123");
        loginPage.checkErrorsMessages("Username must be at least 3 characters.;You must provide a valid email address.");

    }

}
