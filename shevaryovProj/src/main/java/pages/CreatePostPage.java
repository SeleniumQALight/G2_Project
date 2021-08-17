package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {
//    @FindBy(xpath = ".//input[@name='title']")
//    сокращённая запись xpath выше
    @FindBy(name = "title")
    private TextInput inputTitle;

    @FindBy(id = "post-body")
    private TextInput inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSave;

    // элемент DropDown
    @FindBy(xpath = ".//select[@id='select1']")
    private Select dropDownSelectValue;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/create-post";
    }

    public CreatePostPage checkIsInputTitlePresent(){
        Assert.assertTrue("Input title is not present", isElementPresent(inputTitle));
//        возвращаем эту же страницу
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
        // передаем webDriver для того что бы все работало в одном окне
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
//                , baseURL + getRelativeURL()
//                , webDriver.getCurrentUrl() );
        checkURL();
        return this;
    }
}
