package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTestWithoutSoftAssertions extends BaseTest {
    @Test
    public void validateRegistrationErrors() {
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration("12")
                .enterEmailInRegistration("qqq")
                .enterPassWordInRegistration("345")
                .checkErrors("Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
