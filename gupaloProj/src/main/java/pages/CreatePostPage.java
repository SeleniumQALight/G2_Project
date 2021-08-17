package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {


    //@FindBy (xpath = ".//input[@name='title']")
    @FindBy(name = "title")
    private TextInput inputTitle;

    @FindBy(id = "post-body")
    private TextInput inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSave;

    @FindBy(xpath = ".//select[@id='select1']")
    private Select dropDownSelectValue;

    @FindBy(xpath = ".//input[@id = '”UniquePost”']")
    private WebElement checkBoxUniquePost;


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

    public CreatePostPage selectTextInDDSelectValue(String text) {
        selectTextInDD(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDDSelectValue(String value) {
        selectValueInDD(dropDownSelectValue, value);
        return this;
    }

    public CreatePostPage checkIsRedirectOnPostPage() {
//        Assert.assertEquals("Invalid page"
//                , baseUrl + getRelativeUrl()
//                , webDriver.getCurrentUrl()
//        );
        checkURL();
        return this;
    }

    public CreatePostPage selectTextInDropDownByClick(String text) {
        ExpectedConditions.visibilityOf(dropDownSelectValue);
        clickOnElement(dropDownSelectValue);
        clickOnElement(webDriver.findElement(By.xpath(".//select[@id = 'select1']//option[text()='" + text + "']")));
        return this;
    }

    public CreatePostPage clickCheckBoxToSetState (String stateOfCheckBox){
        setCheckboxStateTo(checkBoxUniquePost,stateOfCheckBox);
        Util.waitABit(5);
        return this;
    }
}


