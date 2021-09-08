package registationTest;

import static pages.ParentPage.configProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import baseTest.BaseTest;
import categories.SmokeTestFilter;

import libs.SpreadsheetData;

@RunWith(Parameterized.class)
@Category(SmokeTestFilter.class)
public class RegistrationTestWithExcel extends BaseTest {
    private String login, email, passWord, errors;

    public RegistrationTestWithExcel(String login, String email, String passWord, String errors) {
        this.login = login;
        this.email = email;
        this.passWord = passWord;
        this.errors = errors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
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
