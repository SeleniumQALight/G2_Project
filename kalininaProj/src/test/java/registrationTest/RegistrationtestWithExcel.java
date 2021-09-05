package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.naming.TestCaseName;
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
public class RegistrationtestWithExcel extends BaseTest {

    private String login, email, passWord, errors;

    public RegistrationtestWithExcel(String login, String email, String passWord, String errors){
        this.login = login;
        this.email = email;
        this.passWord = passWord;
        this.errors = errors;
    }

   @Parameterized.Parameters
   public static Collection testData() throws IOException{
       InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
       return  new SpreadsheetData(inputStream, "InvalidLogOn").getData();
   }

    @Test

    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration(login)
        .enterEmailInRegistration(email)
        .enterPasswordRegistration(passWord)
        .checkErrorsMessages(errors)
        ;
    }
}
