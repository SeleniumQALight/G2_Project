package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public boolean isButtonSignOutPresent() {
        return isElementPresent(buttonSignOut);
    }
}
