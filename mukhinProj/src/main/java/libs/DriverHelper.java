package libs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

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

    private  WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if((browser == null) || browser.equalsIgnoreCase("browser")){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("opera")){
            WebDriverManager.operadriver().setup();
            webDriver = new OperaDriver();
        }else if(browser.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.opera.driver", "D:\\Education\\G2Project\\G2_Project\\mukhinProj\\src\\drivers\\msedgedriver.exe");

//            WebDriverManager.edgedriver().setup();
            EdgeDriver driver = new EdgeDriver();
        }else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();

            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }
        return webDriver;
    }
}
