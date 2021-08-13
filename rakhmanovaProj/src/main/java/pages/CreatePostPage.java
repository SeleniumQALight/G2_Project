package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage{
   // @FindBy(xpath = ".//input[@name='title']")
    @FindBy(name = "title")
    private TextInput inputTitle;

    //@FindBy(xpath = ".//textarea[@name='body']")
    @FindBy(id = "post-body")
    private TextInput inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private Button buttonSave;

    @FindBy(xpath = ".//select[@id='select1']")
    private Select dropDownSelectValue;

    String dropDownOptionsLocator = ".//option[text() = '%s']";

    String dropDownSelectValueAllUsers = ".//option[@value='All Users']";

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkBox;

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

    public CreatePostPage selectTextInDropDownByClick(String text) {
        clickOnElement(dropDownSelectValue);
        clickOnElement(webDriver.findElement(By.xpath(String.format(dropDownOptionsLocator, text))));
        return this;
    }

    public CreatePostPage selectCheckBoxState(String checkBoxStatus){
            if(checkBoxStatus.equals("check")){
                if(!checkBox.isSelected()){
                    clickOnElement(checkBox);
                    logger.info("CheckBox is checked");
                }else {
                    logger.info("CheckBox is already checked");
                }
            }else if(checkBoxStatus.equals("unchecked")) {
                if (checkBox.isSelected()) {
                    clickOnElement(checkBox);
                    logger.info("CheckBox is not checked");
                }else {
                    logger.info("CheckBox is already not checked");
                }
            }
        return this;
    }
}
