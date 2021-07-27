package Error_PopUp_SignUp;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class Error_PopUp_SignUp extends BaseTest {
    @Test
    public void Sign__Up() {
        loginPage.openLoginPage();
        loginPage.enterLoginSignUp("tr");
        loginPage.enterEmail("test.com");
        loginPage.enterPassSignUp("123");
        loginPage.clickOnOurApp();
        checkExpectedResult("UserName pop up is not visible ", loginPage.errorPopUpUserName(), true);
        checkExpectedResult("E-mail pop up is not visible ", loginPage.errorPopupEmail(), true);
        checkExpectedResult(" Password is not visible",loginPage.errorPopupPass(),true);

    }
}