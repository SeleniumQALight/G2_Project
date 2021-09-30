package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;
@FindBy(xpath = ".//a[text()='Create Post']")
private Button buttonCreatePost;
    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonProfile;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public boolean isButtonSignOutPresent() {
        return isElementPresent(buttonSignOut);

    }
    @Step

    public HomePage checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not displayed ", isButtonSignOutPresent());
        return this;
    }
    @Step
    public CreatePostPage clickonButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    public ProfilePage clickOnProfileButton() {
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }
}

