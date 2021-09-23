package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage{
    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonProfile;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private Button buttonCreatePost;


    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button profileButton;

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
    public boolean isButtonSignOutPresent(){
        return isElementPresent(buttonSignOut);
    }
    @Step
    public HomePage checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not displayed", isButtonSignOutPresent());
        return this;
    }
    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutPresent()){
            loginPage.loginWithValidCred();
        }
        return this;
    }
    @Step
    public ProfilePage clickOnButtonProfile(){
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnProfileButton() {

        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }
}
