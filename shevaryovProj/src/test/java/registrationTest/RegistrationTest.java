package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@Category(SmokeTestFilter.class)
//Запуск тестов с параметрами (в pom.xml добавить JUnitParams)
@RunWith(JUnitParamsRunner.class)
public class RegistrationTest extends BaseTest {
    // в параметризированом тесте категории не работают
    //@Category(SmokeTestFilter.class)
    @Test
//    запуск теста с двумя наборами параметров по 4 параметра
    @Parameters({
            "12,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "12,qqq,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address.",
    })
//    название теста
    @TestCaseName("registrationErrors: login = {0}, email={1}, passWord={2}")
    public void registrationErrors(String login, String email, String passWord, String errors){
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration(login)
                .enterEmailInRegistration(email)
                .enterPassWordRegistration(passWord)
                .checkErrorsMessage(errors)
        ;
    }
}
