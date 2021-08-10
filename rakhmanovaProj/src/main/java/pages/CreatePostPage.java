package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
   // @FindBy(xpath = ".//input[@name='title']")
    @FindBy(name = "title")
    private WebElement inputTitle;

    //@FindBy(xpath = ".//textarea[@name='body']")
    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSave;

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

    public CreatePostPage enterTextIntoInputTitle(String post_title) {
        enterTextToElement(inputTitle, post_title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String body_text) {
        enterTextToElement(inputBody, body_text);
        return this;
    }

    public PostPage clickOnSaveButton() {
        clickOnElement(buttonSave);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownSelectValue(String text) {
        selectTextInDropDown(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDropDownSelectValue(String value) {
        selectValueInDropDown(dropDownSelectValue, value);
        return this;
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
//        Assert.assertEquals("Invalid page"
//                , baseUrl + getRelativeUrl()
//                , webDriver.getCurrentUrl()
//                );
        checkUrl();
        return this;
    }
}
