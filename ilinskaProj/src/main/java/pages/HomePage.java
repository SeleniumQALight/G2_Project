package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isSignINPresent()
    {
       return isElementPresent(buttonSignIn);
    }

}

