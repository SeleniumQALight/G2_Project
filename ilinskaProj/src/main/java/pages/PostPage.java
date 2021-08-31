package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class PostPage  extends ParentPage {
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private TextBlock successMessageElement;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private Button buttonDelete;
    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonProfile;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public PostPage checkIsButtonDeletePresent() {
        Assert.assertTrue("Button is not present", isElementPresent(buttonDelete));
        return this;
    }

    public PostPage checkIsSucessMessagePresent() {
        Assert.assertTrue("Success message is not present ", isElementPresent(successMessageElement));
        return this;
    }

    public PostPage checkTextinSuccessMessage(String text) {
        String actualText = successMessageElement.getText();
        Assert.assertEquals("Text in message", text, actualText);
        return this;
    }

    public ProfilePage clickonProfile() {
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }
}