package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void registrationAlerts(){
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration("tr");
        loginPage.enterEmailRegistration("test.com");
        loginPage.enterPassWordInRegistration("123");
        loginPage.clickOnButtonSignUp();
        checkExpectedResult("Alert Invalid Username Registration is not displayed", loginPage.isAlertInvalidUsernameRegistrationDisplayed(), true);
        checkExpectedResult("Alert Invalid Email Registration is not displayed", loginPage.isAlertInvalidEmailRegistrationDisplayed(), true);
        checkExpectedResult("Alert Invalid Password Registration is not displayed", loginPage.isAlertInvalidPasswordRegistrationDisplayed(), true);
    }
}
