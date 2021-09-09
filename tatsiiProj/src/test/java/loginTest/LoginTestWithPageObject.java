package loginTest;

import baseTest.BaseTest;

import categories.SmokeTestFilter;
import io.qameta.allure.*;
import junitparams.Parameters;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

//@RunWith(JUnitParamsRunner.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Category(SmokeTestFilter.class)
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN+"333");
        loginPage.enterPassWordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }

    @Test
    @Ignore
    @Category(SmokeTestFilter.class)
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(dataForValidLogin.get("login"));
        loginPage.enterPassWordInSignIn(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }
    @Test
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit("auto", "123");

//        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(),false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(),true);
        checkExpectedResult("label Invalid Login is not present", loginPage.isLabelMessageInvalidLoginPresent(),true);
    }
    @Test
    @Parameters({"auto,123", "auto2,123", "auto3,789"})
    public void paramInvalidLogin(String login, String password){

            loginPage.fillLoginFormAndSubmit(login, password);

            checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(), true);
            checkExpectedResult("label Invalid Login is not present", loginPage.isLabelMessageInvalidLoginPresent(), true);
    }
//    @Test
//    public void registrationValidationMessages(){
//        loginPage.fillRegistrationFormAndSubmit("tr", "test.com", "123");
//
//        checkExpectedResult("Label short Username is not present", loginPage.isLabelMessageShortUsernamePresent(),true);
//        checkExpectedResult("Label valid email is not present", loginPage.isLabelMessageValidEmailPresent(),true);
//        checkExpectedResult("Label password is not 12 characters", loginPage.isLabelMessagePasswordPresent(),true);
//    }
}
