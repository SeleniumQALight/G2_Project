package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

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
}
