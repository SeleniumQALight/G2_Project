package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{

//    кнопка Sign Out
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

//    конструктор
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

//    наличие кнопки SignOut
    public boolean isButtonSignOutPresent(){
        return isElementPresent(buttonSignOut);
    }
}
