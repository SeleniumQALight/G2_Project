package login;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Inv_Login {
    private ChromeDriver webDriver;

    @Test
    public void InvalidLOg() throws InterruptedException {
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
        {
        if (webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center' and text()='Invalid username / password']")).isDisplayed())
            {
                System.out.println("You entered invalid credentials! Please check one more time!");
                webDriver.quit();
            }
        }
    }
}
