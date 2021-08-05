package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTestWithPageObject extends BaseTest {
    @Test
    public void ValidationMessagesInRegistrationFields() {
        loginPage.openLoginPage();
        loginPage.fillRegistrationFormAndSubmit("tr", "test.com", "1111111111111111");
//        checkExpectedResult("Registration username alert is missed", loginPage.isRegistrationUsernameAlertPresent(), true);
//        checkExpectedResult("Registration email alert is missed", loginPage.isRegistrationEmailAlertPresent(), true);
//        checkExpectedResult("Registration password alert is missed", loginPage.isRegistrationPasswordAlertPresent(), true);
        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");

    }
}
