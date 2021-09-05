package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreatePostPage extends ParentPage {
    //@FindBy (xpath = ".//input[@name='title']")
    @FindBy (name = "title")
    private TextInput inputTitle;

    @FindBy (id = "post-body")
    private TextInput inputBody;

    @FindBy (xpath=".//button[text()='Save New Post']")
    private Button buttonSave;

    @FindBy (xpath = ".//select[@id='select1']")
    private Select dropDownSelectValue;

    @FindBy (xpath = ".//input[@id='”UniquePost”']")
    private WebElement checkBoxUniquePost;

    String textInDropDownMenu = ".//*[text()='%s']";

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        checkUrl();
        return this;
    }

    public CreatePostPage checkIsInputTitlePresent() {
        Assert.assertTrue("Input title is not present", isElementPresent(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String post_title) {
        enterTextToElement(inputTitle, post_title);
        return this;
    }

    public CreatePostPage enterBodyIntoInputTitle(String body_text) {
        enterTextToElement(inputBody, body_text);
        return this;
    }

    public PostPage clickOnSaveButton() {
        clickOnElement(buttonSave);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDDSelect_value(String text) {
        selectTextInDD(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDDSelectValue(String value) {
        selectValueInDD(dropDownSelectValue, value);
        return this;
    }

    public CreatePostPage selectTextInDropDownByClick(String text) {
        ExpectedConditions.visibilityOf(dropDownSelectValue);
        clickOnElement(dropDownSelectValue);
        clickOnElement(webDriver.findElement(By.xpath((String.format(textInDropDownMenu, text)))));
        return this;
    }

    public CreatePostPage changeUniquePostState(String text){
        switch (text) {
            case "check":
                if (checkBoxUniquePost.isSelected()) {
                    logger.info("'This is a unique post' check-box is already marked");
                } else {
                    clickOnElement(checkBoxUniquePost);
                    logger.info("'This is a unique post' check-box has been marked");
                }
                break;
            case "uncheck":
                if (checkBoxUniquePost.isSelected()) {
                    clickOnElement(checkBoxUniquePost);
                    logger.info("'This is a unique post' check-box has been unmarked");
                }
                else {
                    logger.info("'This is a unique post' check-box is already unmarked");
                }
                break;
            default:
                logger.info("Incorrect String value has been used. Please use check or uncheck only");
                break;

        }
        return this;
    }
}
