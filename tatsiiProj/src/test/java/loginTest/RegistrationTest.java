package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @org.junit.Test
    public void registrationValidationMessages(){
        // registrationForm.fillRegistrationFormAndSubmit("tr", "test.com", "123");
        registrationForm.fillRegistrationFormAndSubmit("tr", "test.com", "");

        String errors = "Username must be at least 3 characters;You must provide a valid email address.";
        checkExpectedResult("Wrong Error messages", registrationForm.checkErrors(errors), true);

        // checkExpectedResult("Label short Username is not present", registrationForm.isLabelMessageShortUsernamePresent(),true);
        // checkExpectedResult("Label valid email is not present", registrationForm.isLabelMessageValidEmailPresent(), true);
        // checkExpectedResult("Label password is not 12 characters", registrationForm.isLabelMessagePasswordPresent(), true);
    }
}
