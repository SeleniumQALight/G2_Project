package registration;

import baseTest.BaseTest;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ParentPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

//@RunWith(JUnitParamsRunner.class)
@RunWith(Parameterized.class)
public class RegistrationTestWithExcel extends BaseTest {
    private String login, email, password, errors;

    public RegistrationTestWithExcel(String login, String email, String password, String errors) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.errors = errors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(ParentPage.configProperties.DATA_FILE_PATH()+"testDataSuit.xls");
        return new SpreadsheetData(inputStream, "InvalidLogOn").getData();
    }



    @Test
    public void validationLoginAndEmailOnRegistration(){
        loginPage.openLoginPage();
        loginPage.enterUsernameInSignUp(login);
        loginPage.enterEmailInSignUp(email);
        loginPage.enterPasswordInSignUp(password);
        loginPage.clickOnButtonSignUp();

        loginPage.checkErrors(errors);
    }

}
