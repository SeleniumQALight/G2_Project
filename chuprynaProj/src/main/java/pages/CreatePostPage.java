package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {
    //    @FindBy(xpath = ".//input[@name='title']")
    @FindBy(name = "title")
    private TextInput inputTitle;

    @FindBy(id = "post-body")
    private TextInput inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSavePost;

    @FindBy(xpath = ".//select[@id='select1']")
    private Select dropDownSelectValue;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private CheckBox checkboxUniquePost;

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

    public CreatePostPage selectTextInDDByClickSelectValue(String text) {
        selectTextInDropDownByClick(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectOptionInCheckboxUniquePost(String state) {
        selectStateInCheckbox(checkboxUniquePost, state);
        return this;
    }
}
