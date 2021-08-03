package registration;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationRegistration extends BaseTest {
    @Test
    public void validationRegistration() throws InterruptedException {

        loginPage.RegWithInvalidCred()

                .checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");

    }

}
