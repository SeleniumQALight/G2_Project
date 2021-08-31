package loginTest;

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

import static pages.ParentPage.*;

@RunWith(Parameterized.class)
@Category(SmokeTestFilter.class)
public class RegistrationTestWithPageObjectWithExcel extends BaseTest {
    private String login, email, password, errors;

    public RegistrationTestWithPageObjectWithExcel(String login, String email, String password, String errors) {
        this.login = login;
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
    public void ValidationMessagesInRegistrationFields() {
        loginPage.openLoginPage();
        loginPage.fillRegistrationFormAndSubmit(login, email, password);
//        checkExpectedResult("Registration username alert is missed", loginPage.isRegistrationUsernameAlertPresent(), true);
//        checkExpectedResult("Registration email alert is missed", loginPage.isRegistrationEmailAlertPresent(), true);
//        checkExpectedResult("Registration password alert is missed", loginPage.isRegistrationPasswordAlertPresent(), true);
        //loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");
        loginPage.checkErrorsMessages(errors);

    }
}
