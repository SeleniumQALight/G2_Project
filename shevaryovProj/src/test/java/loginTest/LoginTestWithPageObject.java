package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ExcelDriver;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static pages.ParentPage.configProperties;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    // аннотации отчета Allure
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Test
    // аннотации отчета Allure
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    //указываем категорию в которой будет запускаться тест
    @Category(SmokeTestFilter.class)
    @Step
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPassWordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonInSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }

    // тест с использованием данных для теста из Excel
    @Test
    //@Ignore //Пропуск теста
    @Category(SmokeTestFilter.class)
    @Step
    public void validLoginWithExcel() throws IOException {
        // подключаем excel файл и загружаем его в Map
        Map<String, String> dataForValidLogin = ExcelDriver.getData(configProperties.DATA_FILE(), "validLogOn");
        loginPage.openLoginPage();
        // логин и пароль из файла Excel
        loginPage.enterLoginInSignIn(dataForValidLogin.get("login"));
        loginPage.enterPassWordInSignIn(dataForValidLogin.get("pass"));
        loginPage.clickOnButtonInSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(), true);
    }

}
