package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration("12")
                .enterEmailInRegistration("qqq")
//                .enterPassWordRegistration("123")
                .checkErrorsMessage("Username must be at least 3 characters.;You must provide a valid email address.")

        ;
    }
}
