package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends ParentPage{

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

    public ProfilePage checkIsRedirectToProfilePage(){
        checkUrlWithPattern();
        return this;
    }

    public ProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title))
                                                           );
        Assert.assertEquals("Number of posts with title", 1, postList.size());
        return this;
    }

    public ProfilePage deletePostWithTitleWhilePresent(String post_title) {
        List<WebElement> listOfPost = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title))
                                                            );

        int counter = 0;
        while (!listOfPost.isEmpty() && counter <100){
            clickOnElement(webDriver.findElement(By.xpath(
                    String.format(postTitleLocator, post_title)
            )));
            new PostPage(webDriver)
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletePostMessegePresent();

            listOfPost = webDriver.findElements(
                    By.xpath(String.format(postTitleLocator, post_title))
            );
            counter++;
        }
        return this;
    }

    private ProfilePage checkIsSuccessDeletePostMessegePresent() {
        Assert.assertTrue("Element is not present", isElementPresent(successPostDeleteElement));
        return this;
    }
}
