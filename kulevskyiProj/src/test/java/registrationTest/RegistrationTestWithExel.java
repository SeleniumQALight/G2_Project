package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;


@RunWith(Parameterized.class)
public class RegistrationTestWithExel extends BaseTest {
    private String login, email, passWord, errors;

    public RegistrationTestWithExel(String login, String email, String passWord, String errors) {
        this.login = login;
        this.email = email;
        this.passWord = passWord;
        this.errors = errors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(".//src//main//java//data//" + "testDataSuit.xls");
     return new SpreadsheetData(inputStream, "InvalidLogOn").getData();
    }


    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegistration(login)
                .enterEmailInRegistration(email)
                .enterPasswordInRegistration(passWord)
                .checkErrorsMessages(errors);

    }
}
