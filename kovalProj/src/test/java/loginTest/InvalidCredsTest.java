package loginTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class InvalidCredsTest {
    WebDriver webDriver;


    @Test
    public void invalidLogin(){
        File fileFF = new File("./src/drivers/91/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");
        webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).sendKeys("auto1");
        System.out.println("'auto1' was inputted");
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys("12345qwerty");
        System.out.println("Invalid pass was inputted");
        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");
        Assert.assertTrue("Invalid creds message is displayed", invalidCredsWarning());
        System.out.println("invalid Creds message is displayed");
        webDriver.quit();


    }

    private boolean invalidCredsWarning() {

        if (webDriver.findElement((By.xpath(".//div[@class='alert alert-danger text-center']"))).isDisplayed())
            return true;
        else
            return false;

        }

    }






