package registrationPage;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class RegistrationPage extends BaseTest {
    @Test
    @Parameters({
            "12, nssda, 123, Username must be at least 3 characters.;You must provide a valid email address.; Password must be at least 12 characters.",
            "12, nssda, 123456qwerty, Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")

    public void restrationErrors(String login, String email, String password, String errors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegistration(login)
                .enterEmailInRegistration(email)
                .enterPassWordRegistration(password)
                .checkErrorsMessages(errors)
                ;

    }
}
