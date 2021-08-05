package registrationPage;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationPage extends BaseTest {
    @Test
    public void restrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegistration("12")
                .enterEmailInRegistration("nssda")
              //  .enterPassWordRegistration("123")
                .checkErrorsMessages("Username must be at least 3 characters.;You must provide a valid email address.")
                ;

    }
}
