package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='title']")
//    or
//    @FindBy(name = "title")
    private TextInput inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private TextInput inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSaveNewPost;

    @FindBy(xpath = ".//select[@id='select1']")
    private Select dropDownSelectValue;

    @FindBy(id = "”UniquePost”")
    private WebElement checkboxUniquePost;

    // --------------------------------------------------------------------------------------------------
    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/create-post";
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

    public PostPage clickOnSaveNewPost() {
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
        checkURL();
        return this;
    }

    public CreatePostPage uniquePostCheckbox(String status) {
        checkCheckBox(checkboxUniquePost, status);
        return this;
    }

    public CreatePostPage selectTextInDDByClick (String textInDD) {
        selectTextInDropdown(dropDownSelectValue, textInDD);
        return this;
    }
}
