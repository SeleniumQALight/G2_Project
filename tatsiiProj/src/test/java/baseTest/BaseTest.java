package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationForm;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

public class BaseTest {
    WebDriver webDriver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected RegistrationForm registrationForm;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        registrationForm = new RegistrationForm(webDriver);
    }
    @After
    public void tearDown(){
        webDriver.quit();
    }



    protected void checkExpectedResult(String message, boolean actualResult, boolean expectedResult){
        Assert.assertThat(message, actualResult, is(expectedResult));
    }
}
