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
import pages.ProfilePage;
import postsTest.CreatePostTest;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

public class BaseTest {
    WebDriver webDriver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ProfilePage myProfilePage;
    protected Logger logger = Logger.getLogger(getClass());

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() {
        logger.info("---------- " + testName.getMethodName() + " was started ----------");
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);

    }

    @After
    public void tearDown() {
        loginPage = new LoginPage(webDriver);

        // Close browser
        webDriver.quit();
        logger.info("---------- " + testName.getMethodName() + " was finished ----------");
        System.out.println("Quit browser");
    }

    protected void checkExpectedResult(String message, boolean actualResult, boolean expectedResult) {
        Assert.assertThat(message, actualResult, is(expectedResult));
    }
}
