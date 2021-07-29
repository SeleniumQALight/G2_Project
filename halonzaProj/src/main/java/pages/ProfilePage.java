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

    public ProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title))
        );
        Assert.assertEquals("Number of posts with title " + post_title
                , 1, postList.size());
        return this;
    }

    public ProfilePage deletePostWithTitleWhilePresent(String post_title) {
        List<WebElement> listOfPosts = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title))
        );
        int counter = 0;
        while (!listOfPosts.isEmpty() && counter<100) {
            clickOnElement(webDriver.findElement(By.xpath(
                    String.format(postTitleLocator, post_title)
            )));
            new PostPage(webDriver)
                    .clickOnDeleteButton()
            .checkIsSuccessDeletePostMessagePresent();
            listOfPosts = webDriver.findElements(
                    By.xpath(String.format(postTitleLocator, post_title))
            );
            counter++;
        }
        return this;
    }

    public ProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("SuccessDeletePostMessage is not present", isElementPresent(successPostDeleteElement));
        return this;
    }
}
