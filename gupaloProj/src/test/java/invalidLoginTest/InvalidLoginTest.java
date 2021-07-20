package invalidLoginTest;

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
    public void invalidLogin(){
        File fileFF = new File("./src/drivers/91/chromedriver (3).exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
        System.out.println("Site was opened");

        webDriver.findElement(By.xpath(".//input[@placeholder='Username']")).clear();
        webDriver.findElement(By.xpath(".//input[@placeholder='Password']")).clear();
        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        System.out.println("Button was clicked");

        Assert.assertTrue("The validate message is not appeared", isValidateMessageVisible());

        webDriver.quit();


    }

    private boolean isValidateMessageVisible() {
        return webDriver.findElement(By.xpath(".//div[text()='Invalid username / password']")).isDisplayed();
    }
}
