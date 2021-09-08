package login;
import baseTest.BaseTest;
import categories.SmokeTestFilter;
import org.junit.Test;
import org.junit.Assert;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.util.concurrent.TimeUnit;
public class LoginTest  extends BaseTest {
    @Category(SmokeTestFilter.class)
    WebDriver webDriver;
@Test
    public void validLogin() {
        File fileFF = new File("./src/drivers/91/91-1/chromedriver (2).exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");
        webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).sendKeys("auto");
        System.out.println("'auto was inputted");
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("pass was inputted");
        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");
        Assert.assertTrue("Button Signout is not displayed", isButtonSignOutVisible());
    }
    public boolean isButtonSignOutVisible() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    @Test
     public void invalidLOg() {
            File fileFF = new File("./src/drivers/91/91-1/chromedriver (2).exe");
            System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            System.out.println("Site was opened");
            webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).clear();
            webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).sendKeys("test");
            System.out.println("test was inputted");
            webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
            webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty11");
            System.out.println("pass was inputted");
            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            System.out.println("Button was clicked");
            Assert.assertTrue("Error Message appeared! Please try one more time!", isPopupDisplay());
        }
    private boolean isPopupDisplay() {
        try{
            return webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center' and text()='Invalid username / password']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
