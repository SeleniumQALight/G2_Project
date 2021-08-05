package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class Registrationtest extends BaseTest {
    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration("12")
        .enterEmailInRegistration("qqq")
        .enterPasswordRegistration("12")
        .checkErrorsMessages("Username must be at least 3 characters;You must provide a valid email address.")
        ;


    }
}
