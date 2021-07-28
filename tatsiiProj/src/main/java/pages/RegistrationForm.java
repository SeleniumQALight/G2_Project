package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationForm extends ParentPage {
    public RegistrationForm(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isLabelMessageShortUsernamePresent(){
        return isElementPresent(webDriver.findElement(By.xpath("//div[contains(text(),'Username must be at least 3 characters.')]")));
    }
    public boolean isLabelMessageValidEmailPresent() {
        return isElementPresent(webDriver.findElement(
                By.xpath("//div[contains(text(),'You must provide a valid email address.')]")));
    }

    public boolean isLabelMessagePasswordPresent() {
        return isElementPresent(webDriver.findElement(
                By.xpath("//div[contains(text(),'Password must be at least 12 characters.')]")));
    }
}
