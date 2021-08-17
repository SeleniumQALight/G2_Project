package registrationTest2;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ParentPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@RunWith(Parameterized.class)
@Category(SmokeTestFilter.class)
public class RegistrationTest2WithExel extends BaseTest {
    private String login, email, passWord, errors;

    public RegistrationTest2WithExel(String login, String email, String passWord, String errors) {
        this.login = login;
        this.email = email;
        this.passWord = passWord;
        this.errors = errors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(ParentPage.configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "InvalidLogOn").getData();
    }

    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegistration(login)
                .enterEmailInRegistration(email)
                .enterPassWordRegistration(passWord)
                .checkErrorsMessages(errors)
                ;
    }
}
