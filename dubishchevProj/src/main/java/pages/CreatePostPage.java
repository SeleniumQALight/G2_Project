package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {
    //@FindBy(xpath = ".//input[@name='title']")
    @FindBy(name = "title")
    private TextInput inputTitle;
    @FindBy(id = "post-body")
    private TextInput inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button saveButton;
    @FindBy(id = "select1")
    private Select dropDownSelectValue;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsTitlePresent() {
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
        clickOnElement(saveButton);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDDSelectValue(String text) {
        selectTextInDropDown(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDDSelectValue(String value) {
        selectValueInDropDown(dropDownSelectValue, value);
        return this;
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        checkUrl();
        return this;
    }
}
