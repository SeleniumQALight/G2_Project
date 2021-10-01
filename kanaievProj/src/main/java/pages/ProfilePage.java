package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

public class ProfilePage extends ParentPage {
    String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private HtmlElement successPostDeleteElement;
    @FindBy(xpath = ".//*[@class='list-group']/a")
    private List<WebElement> postsList;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public ProfilePage checkIsRedirectOnProfilePage(){
        checkUrlWithPattern();
        return this;
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
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, postTitle))), "Post Title");
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

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
    }
}
