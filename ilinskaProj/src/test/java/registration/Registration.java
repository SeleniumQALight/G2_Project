package registration;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class Registration  extends BaseTest {
    @Test
    @Parameters({
            "12,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "12,qqq,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."

    })
    @TestCaseName("registration:login={0},email={1},password={2}" )
    public void registrationErrors(String login, String email, String password, String errors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration(login)
                .enterEmailRegistration(email)
                .enterPasswordRegistration(password)
                .checkErrorsMessages(errors);

        }

    }

