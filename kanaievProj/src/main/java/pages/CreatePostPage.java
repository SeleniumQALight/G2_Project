package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreatePostPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(id = "select1")
    private WebElement dropDownSelectValue;

    @FindBy(id = "”UniquePost”")
    private WebElement checkBoxUniquePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsInputTitlePresent() {
        Assert.assertTrue("Input Title is not present", isElementPresent(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String postTitle) {
        enterTextToElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyText) {
        enterTextToElement(inputBody, bodyText);
        return this;
    }

    public PostPage clickButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDDSelectValue(String text) {
        selectTextInDD(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDDSelectValue(String value) {
        selectValueInDD(dropDownSelectValue, value);
        return this;
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        checkUrl();
        checkIsInputTitlePresent();
        return this;
    }

    public CreatePostPage selectTextInDropDownByClick(String text) {
        clickOnElement(dropDownSelectValue);
        clickOnElement(
                webDriverWait10.until(ExpectedConditions
                        .elementToBeClickable(By
                                .xpath(".//select[@id='select1']//option[text()='" + text + "']")))
        );
        return this;
    }

    public CreatePostPage clickCheckBoxToSetState(String stateOfCheckBox) {
        setCheckBoxStateTo(checkBoxUniquePost, stateOfCheckBox);
        Util.waitABit(5);
        return this;
    }
}
