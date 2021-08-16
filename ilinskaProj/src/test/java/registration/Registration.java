package registration;

import baseTest.BaseTest;
import org.junit.Test;

public class Registration  extends BaseTest {
    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration("12")
                .enterEmailRegistration("qqq")
//                .enterPasswordRegistration("345")
                .checkErrorsMessages("Username must be at least 3 characters.;You must provide a valid email address.");


        }

    }

