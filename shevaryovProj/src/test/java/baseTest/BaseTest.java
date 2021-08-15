package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

public class BaseTest {
    WebDriver webDriver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected Logger logger = Logger.getLogger(getClass());

    // указываем название теста
    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() {
        // логирование название метода
        logger.info("------ " + testName.getMethodName() + " was started ----------");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //webDriver.manage().window().maximize();

        loginPage = new LoginPage(webDriver);
        homePage= new HomePage(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("------" + testName.getMethodName() + " was ended ----------");
    }

    //    проверка ожидаемого результата
    protected void checkExpectedResult(String message, boolean actualResult, boolean expectedResult) {
//        assertThat - проверка соответствия одного результата (actualResult) другому is(expectedResult)
        Assert.assertThat(message, actualResult, is(expectedResult));
//        то же что и следующая строка
//        Assert.assertEquals(message, expectedResult, actualResult);

    }
}
