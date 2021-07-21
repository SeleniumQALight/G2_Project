package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webdriver;

    public ParentPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }
}
