package loginTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver webDriver;

    @Test
    public void validLogin() {
        File fileFF = new File("./src/drivers/91/chromedriver");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        // Manage browser and add timeout
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Open
        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        // Input username
        webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).sendKeys("auto");
        System.out.println("Username was inputted");

        // Input password
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("123456qwerty");
        System.out.println("Password was inputted");

        // CLick Sign In
        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button 'Sign In' was clicked");

        // CLick Sign Out
        Assert.assertTrue("Button 'Sign Out' was NOT found", isButtonSignOutVisible());
        System.out.println("Button 'Sign Out' was found");

        // Close browser
        webDriver.quit();
        System.out.println("Quit browser");

    }

    private boolean isButtonSignOutVisible() {
        try{
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }
}
