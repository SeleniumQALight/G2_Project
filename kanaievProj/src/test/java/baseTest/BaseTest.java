package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

public class BaseTest {
    WebDriver webDriver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected Logger logger = Logger.getLogger(getClass());
    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp(){
        logger.info("------" + testName.getMethodName() + " was started ------");
        initDriver();
        webDriver.manage().timeouts().implicitlyWait(configProperties.TIME_FOR_DEFAULT_WAIT(), TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
    }
    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("------" + testName.getMethodName() + " was ended ------");
    }

    protected void checkExpectedResult(String message, boolean actualResult, boolean expectedResult){
//        Assert.assertThat(message, actualResult, is(expectedResult));
        Assert.assertEquals(message, expectedResult, actualResult);
    }

    private void initDriver(){
        String browser = System.getProperty("browser");
        if ((browser == null) || browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("ff")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            //WebDriverManager.iedriver().setup();

            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            webDriver = new InternetExplorerDriver();
        }
    }
}
