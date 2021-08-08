package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProfilePage extends ParentPage{
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public ProfilePage checkIsPostWasAdded(String postTitle) {
        List<WebElement> postsList = webDriver.findElements(
                By.xpath(String.format(".//*[text()='%s']", postTitle)));
        Assert.assertEquals("Number of posts with title " + postTitle,
                1, postsList.size());
        return this;
    }
}
