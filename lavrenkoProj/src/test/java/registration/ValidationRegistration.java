package registration;

import baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith(JUnitParamsRunner.class)

public class ValidationRegistration extends BaseTest {
    @Test
//    @Parameters({"12,dsd,322"})
    public void validationRegistration() throws InterruptedException {

        String warning = "Username must be at least 3 characters.;You must provide a valid email address.";
        loginPage.RegWithInvalidCred();
//
//        loginPage.openLoginPage();
//        loginPage.enterUsernameInSignUp("az");
//        loginPage.enterEmailInSignUp("azaz.com");
//        loginPage.enterPasswordInSignUp("123456qwerty");
        loginPage.checkErrors(warning);

    }

}
