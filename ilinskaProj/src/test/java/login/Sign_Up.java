package login;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class Sign_Up extends BaseTest {
    @Test
    public void Sign__Up() {
        loginPage.openLoginPage();
        loginPage.enterLoginSignUp("tr");
        loginPage.enterEmail("test.com");
        loginPage.enterPassSignUp("123");
        loginPage.clickOnOurApp();
        checkExpectedResult("UserName pop up is not visible ", loginPage.errorPopUpUserName("UserName pop up is visible"), true);
        checkExpectedResult("E-mail pop up is not visible ", loginPage.errorPopupEmail("E-mail pop up is visible "), true);
        checkExpectedResult(" Password is not visible",loginPage.errorPopupPass("E-mail pop up is  visible "),true);

    }
}