package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {
    //    @FindBy(xpath = ".//input[@name='title']")
    @FindBy(name = "title")
    private TextInput inputTitle;

    @FindBy(id = "post-body")
    private TextInput inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSave;

    @FindBy(xpath = ".//select[@id='select1']")
    private Select dropDownSelectValue;

    @FindBy(xpath = ".//input[@type='checkbox']]")
    private Select createPostCheckBox;

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

    public CreatePostPage enterTextIntoInputBody(String post_body) {
        enterTextToElement(inputBody, post_body);
        return this;
    }

    public PostPage clickOnSaveButton() {
        clickOnElement(buttonSave);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDDSelectValue(String text) {
        {
            selectTextInDD(dropDownSelectValue, text);
        }
        return this;
    }

    public CreatePostPage selectValueInDDSelectValue(String value) {
        {
            selectValueInDD(dropDownSelectValue, value);
        }
        return this;
    }

    public CreatePostPage selectTextInDDByClickSelectValue(String value) {
        {
            selectTextInDropDownByClick(dropDownSelectValue, value);
        }
        return this;
    }

    public CreatePostPage setValueInCreatePostCheckBox(String value) {
        {
            setValueInCheckBox(createPostCheckBox, value);
        }
        return this;
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
//        Assert.assertEquals("Invalid page "
//                , baseUrl + getRelativeUrl()
//                , webDriver.getCurrentUrl()
//        );
        checkUrl();
        return this;
    }
}
