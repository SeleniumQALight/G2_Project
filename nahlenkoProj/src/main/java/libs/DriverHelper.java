package libs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverHelper {
    private static WebDriver webDriver;

    public void createDriver(){
        webDriver = initDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public void closeDriver(){
        webDriver.quit();
    }

    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if ((browser == null) || browser.equalsIgnoreCase("chrome")){
            /*File fileFF = new File("/Users/User/IdeaProjects/G2_Project_1/drivers/94/chromedriver_mac64_m1/chromedriver");
            System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());*/
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("ff")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            //WebDriverManager.iedriver().setup();

            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            webDriver = new InternetExplorerDriver();
        }
        return webDriver;
    }
}