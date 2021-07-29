package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProfilePage extends PostPage{
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement> postsList = webDriver.findElements(
                By.xpath(String.format(".//*[text()='%s']", post_title))
        );
        Assert.assertEquals("Number of posts with title" + post_title, 1, postsList.size());
        return this;
    }
}
