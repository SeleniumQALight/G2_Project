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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

public class BaseTest {
    WebDriver webDriver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected Logger logger = Logger.getLogger(getClass());

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp(){
        logger.info("-----" + testName.getMethodName() + " was started ----------");
        webDriver = initDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        webDriver.manage().window().maximize();

        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("-----" + testName.getMethodName() + " was finished ----------");
    }

    protected void checkExpectedResult(String message, boolean actualResult, boolean expectedResult){
//        Assert.assertThat(message,actualResult, is(expectedResult));
        Assert.assertEquals(message, expectedResult, actualResult);
    }

    private  WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if((browser == null) || browser.equalsIgnoreCase("browser")){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("opera")){
            WebDriverManager.operadriver().setup();
            webDriver = new OperaDriver();
        }else if(browser.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.opera.driver", "D:\\Education\\G2Project\\G2_Project\\mukhinProj\\src\\drivers\\msedgedriver.exe");

//            WebDriverManager.edgedriver().setup();
            EdgeDriver driver = new EdgeDriver();
        }else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();

            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }
        return webDriver;
    }
}
