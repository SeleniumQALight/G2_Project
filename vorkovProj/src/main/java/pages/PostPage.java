package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class PostPage extends ParentPage {
    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/";
    }

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private Button deletePostButton;

    @FindBy(xpath = ".//div[text()='New post successfully created.']")
    private WebElement alertPostSuccess;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    public PostPage checkRedirectWithParamOnPostPage() {
        checkURLWithPattern();
        checkIsDeletePostButtonPresent();
        return this;
    }

    public PostPage checkIsSuccessMessagePresent() {
        Assert.assertTrue("Success message isn't present", isElementPresent(successMessage));
        return this;
    }

    public ProfilePage checkIsDeletePostButtonPresent() {
        Assert.assertTrue("Delete button isn't present", isElementPresent(deletePostButton));
        return new ProfilePage(webDriver);
    }

    public PostPage checkTextInSuccessMessage(String text) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text isn't equals", text, actualText);
        return this;
    }

    public ProfilePage clickOnDeletePostButton() {
        clickOnElement(deletePostButton);
        return new ProfilePage(webDriver);
    }
}
