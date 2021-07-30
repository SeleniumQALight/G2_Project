package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @org.junit.Test
    public void registrationValidationMessages(){
        registrationForm.fillRegistrationFormAndSubmit("tr", "test.com", "123");

        checkExpectedResult("Label short Username is not present", registrationForm.isLabelMessageShortUsernamePresent(),true);
        checkExpectedResult("Label valid email is not present", registrationForm.isLabelMessageValidEmailPresent(), true);
        checkExpectedResult("Label password is not 12 characters", registrationForm.isLabelMessagePasswordPresent(), true);
    }
}
