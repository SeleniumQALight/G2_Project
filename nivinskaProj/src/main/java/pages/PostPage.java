package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private Button buttonDelete;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonProfile;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public PostPage checkIsRedirectOnPostPage() {
        checkUrlWithPattern();
        chechIsButtonDeletePresent();
        return this;
    }

    public PostPage chechIsButtonDeletePresent() {
        Assert.assertTrue("Button Delete is not present", isElementPresent(buttonDelete));
        return this;
    }

    public PostPage checkIsSuccessMessagePresent() {
        Assert.assertTrue("Success message is not present", isElementPresent(successMessageElement));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String text) {
        Util.waitABit(1);
        String actualText = successMessageElement.getText();
        Assert.assertEquals("Text in message", text, actualText);
        return this;
    }

    public ProfilePage clickOnButtonProfile() {
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new ProfilePage(webDriver);
    }

}
