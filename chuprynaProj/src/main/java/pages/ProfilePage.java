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

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public ProfilePage checkIsPostWasAdded(String postTitle) {
//        List<WebElement> postsList = webDriver.findElements(
//                By.xpath(String.format(postTitleLocator, postTitle)));
        List<WebElement> postsList = getListOfPostsByTitle(postTitle);
        Assert.assertEquals("Number of posts with title " + postTitle,
                1, postsList.size());
        return this;
    }

    private List<WebElement> getListOfPostsByTitle(String postTitle) {
        return webDriver.findElements(
                By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public ProfilePage deletePostWithTitleWhilePresent(String postTitle) {
//        List<WebElement> postsList = webDriver.findElements(
//                By.xpath(String.format(postTitleLocator, postTitle)));
        List<WebElement> postsList = getListOfPostsByTitle(postTitle);

        int counter = 0;
        while (!postsList.isEmpty() && counter < 100) {
            clickOnElement(webDriver.findElement(
                    By.xpath(String.format(postTitleLocator, postTitle)
                    ))); //note that we find only one element

            new PostPage(webDriver)
                    .clickOnButtonDelete()
                    .checkIsSuccessDeletePostMessagePresent();
            //to update list of posts
            postsList = getListOfPostsByTitle(postTitle);
            counter++; //to avoid looping
        }

        return this;
    }

    public ProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Success delete message is not present", isElementPresent(successPostDeleteElement));
        return this;
    }
}
