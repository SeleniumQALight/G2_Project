package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    //    @FindBy(xpath = ".//input[@name='title']")
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSavePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsInputTitlePresent() {
        Assert.assertTrue("Input title is not present", isElementPresent(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoPostTitleInput(String postTitle) {
        enterTextToElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextIntoPostBodyInput(String postBody) {
        enterTextToElement(inputBody, postBody);
        return this;
    }

    public PostPage clickOnButtonSavePost() {
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }

    public CreatePostPage checkIsRedirectedOnCreatePostPage() {
        Assert.assertEquals("Invalid page url",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl()
        );
        return this;
    }
}
