package login;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
@Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginIn(TestData.VALIG_LOGin);
        loginPage.enterPasswwordInSign("123456qwerty");
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible ",homePage.isButtonSignOutPresent(),true);

    }


}
