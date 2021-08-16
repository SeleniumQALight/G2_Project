package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends ParentPage {


    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile";
    }

    public ProfilePage checkisPostwasAdded(String post_title) {
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(".//*[text()='%s']", post_title))
        );
        Assert.assertEquals("Number of posts with title"+ post_title,1,postList.size());
        return this;
    }
}