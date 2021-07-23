package baseTest;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class BaseTest {
    WebDriver webDriver;
    protected LoginPage loginPage;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        loginPage = new LoginPage(webDriver);
    }
    @After
    public void tearDown(){
        webDriver.quit();
    }
}
