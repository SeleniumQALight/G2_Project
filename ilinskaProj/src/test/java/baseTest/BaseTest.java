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

public class BaseTest {
    WebDriver webDriver;
   protected LoginPage loginPage;
   protected HomePage homePage;
   protected Logger logger= Logger.getLogger(getClass());
   @Rule
   public TestName testName= new TestName();
    @Before
    public  void setUp(){
        logger.info("------"+testName.getMethodName()+"was started ---------------------");
   WebDriverManager.chromedriver().setup();
        webDriver= new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        loginPage= new LoginPage(webDriver);
        homePage= new HomePage(webDriver);
    }
    @After
    public  void tearDown(){
        webDriver.quit();
        logger.info("------"+testName.getMethodName()+"was finished  ---------------------");


    }
    protected void checkExpectedResult(String message,boolean actualResult,boolean expectedResult){
        Assert.assertEquals(message,expectedResult,actualResult);

    }

}
