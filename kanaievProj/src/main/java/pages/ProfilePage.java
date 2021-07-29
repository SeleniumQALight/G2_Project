package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends ParentPage {
    String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private WebElement successPostDeleteElement;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage checkIsPostWasAdded(String postTitle) {
        List<WebElement> postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
        Assert.assertEquals("Number of posts with title " + postTitle, 1, postsList.size());
        return this;
    }

    public ProfilePage deletePostWithTitleWhilePresent(String postTitle) {
        List<WebElement> postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
        int counter = 0;
        while (!postsList.isEmpty() && counter < 100) {
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, postTitle))));
            new PostPage(webDriver)
                    .clickOnButtonDelete()
                    .checkIsSuccessDeletePostMessagePresent();
            postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
            counter++;
        }
        return this;
    }

    public ProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Element is not present", isElementPresent(successPostDeleteElement));
        return this;
    }
}
