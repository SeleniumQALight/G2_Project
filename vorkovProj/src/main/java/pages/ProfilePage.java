package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

public class ProfilePage extends ParentPage {
    String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonMyProfile;

    @FindBy(xpath = ".//a[@href='/profile/auto/followers']")
    private WebElement tabFollowers;

    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private TextInput successPostDeleteElement;

    // --------------------------------------------------------------------------------------------------
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/profile";
    }


    public ProfilePage checkIsRedirectionProfilePage() {
        checkURLWithPattern();
        return this;
    }

    public ProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return this;
    }

    public ProfilePage checkIsFollowerPresent() {
        Assert.assertTrue("Followers tab isn't present", isElementPresent(tabFollowers));
        return this;
    }

    public ProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title)));
        Assert.assertEquals("Count of posts with title " + post_title, 1, postList.size());
        return this;
    }

    public ProfilePage deletePostWithTitleWhilePresent(String post_title) {
        List<WebElement> listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
        int counter = 0;
        while (!listOfPosts.isEmpty() && counter < 100) {
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, post_title))), "Post with title");
            new CreatePostPage(webDriver).clickOnDeletePostButton()
                    .checkIsSuccessDeletePostMessagePresent();
            listOfPosts = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
            counter++;
        }

        return this;
    }

    public ProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Success delete post message isn't present", isElementPresent(successPostDeleteElement));
        return this;
    }

}
