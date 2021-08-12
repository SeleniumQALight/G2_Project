package login;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LoginTestWithPageObject extends BaseTest {
    @Category(SmokeTestFilter.class)
@Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginIn(TestData.VALIG_LOGin);
        loginPage.enterPasswwordInSign("123456qwerty");
        loginPage.clickOnButtonSignIn();
        checkExpectedResult("Button SignOut is not visible ",homePage.isButtonSignOutPresent(),true);

    }



}
