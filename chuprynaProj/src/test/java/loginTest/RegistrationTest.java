package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void fieldsValidation(){
        loginPage.openLoginPage();
        loginPage.enterUsernameInRegistrationForm("tr");
        loginPage.enterEmailInRegistrationForm("test.com");
        loginPage.enterPasswordInRegistrationForm("123456qwerty");
        loginPage.clickOnSignUpButton();

        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");
    }
}
