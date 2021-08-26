package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static pages.ParentPage.configProperties;

@Category(SmokeTestFilter.class)
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
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream,"InvalidLogOn").getData();
    }

    @Test
    public void validateErrorMessage(){
        loginPage.openLoginPage();
        loginPage.
                enterUsernameInSignUp(login).
                enterEmailInSignUp(email).
                enterPasswordInSignUp(password);
        loginPage.checkErrorMessages(errors);
    }
}
