package loginTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class InvalidLoginTest {
    WebDriver webDriver;

    @Test
    public void invalidLogin() {
        File fileFF = new File("./src/drivers/91/91-1/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).sendKeys("invalid_login");
        System.out.println("invalid login was inputted");

        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("invalid_password");
        System.out.println("invalid password was inputted");


        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("button was clicked");

        Assert.assertTrue("The alert message is not displayed", isAlertMessageVisible());
        Assert.assertFalse("The SignOut button is displayed", isButtonSignOutVisible());

        webDriver.quit();
    }

    private boolean isAlertMessageVisible() {
        try {
            return webDriver.findElement(By.xpath(".//div[text()='Invalid username / password']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isButtonSignOutVisible() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
