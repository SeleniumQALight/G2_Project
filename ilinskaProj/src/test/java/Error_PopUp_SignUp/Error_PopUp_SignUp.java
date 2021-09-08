package Error_PopUp_SignUp;

import baseTest.BaseTest;
import org.junit.Test;
public class Error_PopUp_SignUp extends BaseTest {
    @Test
    public void sign__Up() {
        loginPage.openLoginPage();
        loginPage.enterLoginSignUp("tr");
        loginPage.enterEmail("test.com");
        loginPage.enterPassSignUp("123");
        loginPage.clickOnOurApp();
        checkExpectedResult("UserName pop up is not visible ", loginPage.isErrorPopUpUserNamePresent(), true);
        checkExpectedResult("E-mail pop up is not visible ", loginPage.isErrorPopupEmailPresent(), true);
        checkExpectedResult(" Password is not visible",loginPage.isErrorPopupPassPresent(),true);

    }
}