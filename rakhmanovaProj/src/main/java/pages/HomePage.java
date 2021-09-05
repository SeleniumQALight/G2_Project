package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage{
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private Button buttonCreatePost;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonOnProfile;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public HomePage checkIsRedirectOnHomePage(){
        checkUrl();
        checkIsButtonSignOutVisible();
        return this;
    }

    @Step
    public boolean isButtonSignOutPresent() {
        return isElementPresent(buttonSignOut);
    }

    @Step
    public HomePage checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button SignOut is not displayed", isButtonSignOutPresent());
        return this; //new HomePage(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutPresent()) {
            loginPage.loginWithValidCred();
        }
        return this;
    }

    public ProfilePage clickOnButtonProfile() {
        clickOnElement(buttonOnProfile);
        return new ProfilePage(webDriver);
    }
}
