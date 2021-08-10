package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreatePostPage extends ParentPage{
    @FindBy(name="title")
    private TextInput inputTitle;
    @FindBy(id="post-body")
    private TextInput inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSave;
    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public CreatePostPage checkIsInputTitlePresent(){
        Assert.assertTrue("Input title is not present", isElementPresent(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String post_title) {
        enterTextToElement(inputTitle,post_title);
        return this;
    }


    public CreatePostPage enterTexttoBodyTitle(String body_text) {
        enterTextToElement(inputBody,body_text);
        return this;
    }
    public PostPage clickOnSaveButton(){
        clickOnElement(buttonSave);
        return new PostPage(webDriver);
    }
}
