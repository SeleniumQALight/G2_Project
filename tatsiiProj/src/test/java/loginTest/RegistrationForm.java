package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationForm extends BaseTest {
    @org.junit.Test
    public void registrationValidationMessages(){
        loginPage.fillRegistrationFormAndSubmit("tr", "test.com", "123");

        checkExpectedResult("Label short Username is not present", loginPage.isLabelMessageShortUsernamePresent(),true);
        checkExpectedResult("Label valid email is not present", loginPage.isLabelMessageValidEmailPresent(),true);
        checkExpectedResult("Label password is not 12 characters", loginPage.isLabelMessagePasswordPresent(),true);
    }
}
