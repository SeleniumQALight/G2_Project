package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class ParentPage {
    Logger logger = Logger.getLogger(String.valueOf(getClass()));
    WebDriver webDriver;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

}
