package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage {
    // @Find(xpath = ".//input[@name='title']" )
    @FindBy(name = "title")
    private TextInput inputTitle;
    @FindBy(id = "post-body")
    private TextInput inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSave;
    // найти закрытый DropDown (его описать через FindBy)
    @FindBy(xpath = ".//select[@id='select1']")
    private Select dropDownSelectValue;
    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkboxUniquePost;

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

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
//        Assert.assertEquals("Invalid page "
//                , baseUrl + getRelativeUrl()
//                , webDriver.getCurrentUrl()
//        );
        checkUrl();
        return this;
    }

    public CreatePostPage selectTextInDropDownByClick(String text) {
        // найти закрытый DropDown, кликнуть по нему
        dropDownSelectValue.click();
        // найти строку с указанным текстом (используя параметризированный локатор)
//        selectValueInDD(dropDownSelectValue, text);
        WebElement option = dropDownSelectValue.findElement(
                By.xpath(String.format(".//option[contains(text(), '%s')]", text)
                ));
//        ".//*[text()='Общедоступное']" Locator
        // кликнуть по данной строке
        option.click();
        return this;
    }

    //    public CreatePostPage selectTextInDropDownByClick(String text) {
//        WebElement option = dropDownSelectValue.findElement(
//                By.xpath(".//select[@id='select1']"));
//        option.click();
//        return this;
//    }
    public CreatePostPage checkCheckBox(boolean value) {
        if (checkboxUniquePost.isSelected() != value) {
            checkboxUniquePost.click();
            logger.info("CheckBox was clicked");
        }
        return this;
    }

    public CreatePostPage checkCheckBox(String state) {
        boolean value = state.equalsIgnoreCase("check");
        boolean stateUncheck = state.equalsIgnoreCase("uncheck");
        if ((value && (checkboxUniquePost.isSelected() != value)) || (stateUncheck && (checkboxUniquePost.isSelected() == stateUncheck))){
            checkboxUniquePost.click();
            logger.info("CheckBox was clicked");
        } else {
            logger.info("CheckBox is already in state");
        }
        return this;
    }
}
