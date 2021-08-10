package registration;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationRegistration extends BaseTest {
    @Test
    public void validationRegistration() throws InterruptedException{
        loginPage.openLoginPage();
//        loginPage.enterRegistrationUserName(TestData.INVALID_LOGIN);
//        loginPage.enterRegistrationEMail(TestData.INVALID_EMAIL);
//        loginPage.enterRegistrationPassWord(TestData.VALID_PASSWORD);
//
//        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");
    }
}
