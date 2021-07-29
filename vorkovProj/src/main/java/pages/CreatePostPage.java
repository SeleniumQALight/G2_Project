package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='title']")
//    or
//    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//div[text()='New post successfully created.']")
    private WebElement alertPostSuccess;


    // --------------------------------------------------------------------------------------------------
    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsInputTitleIsPresent() {
        Assert.assertTrue("Input title isn't present", isElementPresent(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String POST_TITLE) {
        enterTextToElement(inputTitle, POST_TITLE);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String POST_BODY) {
        enterTextToElement(inputBody, POST_BODY);
        return this;
    }

    public CreatePostPage clickOnSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return this;
    }

    public MyProfilePage checkIsSuccessPostAlertPresent() {
        Assert.assertTrue("Success create post alert isn't present", isElementPresent(alertPostSuccess));
        return new MyProfilePage(webDriver);
    }


}
