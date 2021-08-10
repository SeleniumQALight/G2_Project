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

    @FindBy(xpath = ".//select[@id='select1']")
    private WebElement dropDownSelectValue;

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
        checkUrl();
        return this;
    }

    public CreatePostPage selectTextInDDSelectValue(String text) {
        selectTextInDropDown(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDDSelectValue(String value) {
        selectValueInDropDown(dropDownSelectValue, value);
        return this;
    }
}
