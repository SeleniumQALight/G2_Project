package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public boolean isButtonSignOutPresent(){
            try {
                webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
                logger.info("Button 'Sign Out is present");
                return true;
            } catch (Exception e) {
                logger.error("Button Sign Out is not present"+ e);
                return false;
            }

    }


}

