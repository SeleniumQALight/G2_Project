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

@RunWith(Parameterized.class)
@Category(SmokeTestFilter.class)
public class RegistrationTestWithExcel extends BaseTest {
    private String username, email, password, errors;

    public RegistrationTestWithExcel(String username, String email, String password, String errors) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.errors = errors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "InvalidLogOn").getData();
    }

    @Test
    public void registrationErrors() {
        loginPage.openLoginPage();
        loginPage
                .enterUsernameInRegistrationForm(username)
                .enterEmailInRegistrationForm(email)
                .enterPasswordInRegistrationForm(password)
                .clickOnSignUpButton() //TBU: will be removed after added wait (lesson 8)
                .checkErrorsMessages(errors);
    }
}
