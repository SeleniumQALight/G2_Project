package loginTest;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class LoginTest {
    WebDriver webDriver;
    @Test
    public void validTest(){
        File fileFF = new File("./drivers/chromedriver9204.exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());

        webDriver = new ChromeDriver();


    }
}
