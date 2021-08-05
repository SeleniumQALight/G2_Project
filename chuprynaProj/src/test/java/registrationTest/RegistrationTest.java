package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void registrationErrors() {
        loginPage.openLoginPage();
        loginPage
                .enterUsernameInRegistrationForm("12")
                .enterEmailInRegistrationForm("test.com")
//                .enterPasswordInRegistrationForm("123")
                .checkErrorsMessages("Username must be at least 3 characters.;You must provide a valid email address.");
    }
}
