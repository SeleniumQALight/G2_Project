package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
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

    public HomePage checkIsRedirectOnHomePage() {
        checkUrl();
        checkIsButtonSignOutVisible();
        return this;
    }

    public boolean isButtonSignOutPresent() {
        return isElementPresent(buttonSignOut);
    }


    public HomePage checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button SignOut not visible", isButtonSignOutPresent());
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public ProfilePage clickOnProfileButton() {
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutPresent()) {
            loginPage.loginWithValidCred();
        }
        return this;
    }

}
