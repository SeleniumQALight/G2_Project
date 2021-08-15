package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void registrationErrors() {
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration("12")
                .enterEmailInRegistration("qqq")
                .enterPassWordInRegistration("345")
                .checkErrorsMessages("Username must be at least 3 characters.;You must provide a valid email address.");
    }
}
