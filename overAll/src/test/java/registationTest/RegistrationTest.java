package registationTest;

import org.junit.Test;

import baseTest.BaseTest;

public class RegistrationTest extends BaseTest {
    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegistration("12")
                .enterEmailInRegistration("qqq")
//                .enterPassWordRegistration("345")
               .checkErrorsMessages("Username must be at least 3 characters.;You must provide a valid email address.")
        ;
    }
}
