package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage{
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private Button buttonCreatePost;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonProfile;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//*[text() ='Invalid username / password']")
    private WebElement messageFailCredential;

    @FindBy(xpath = ".//*[text() ='Username must be at least 3 characters.']")
    private WebElement messageFailUserName;

    @FindBy(xpath = ".//*[text() ='You must provide a valid email address.']")
    private WebElement messageFailEmail;

    @FindBy(xpath = ".//*[text() ='Password must be at least 12 characters.']")
    private WebElement messageFailPassWord;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public HomePage checkIsRedirectOnHomePage(){
        checkUrl();
        checkIsButtonSignOutVisible();
        return this;
    }
    @Step
    public boolean isButtonSignOutPresent(){
        return isElementPresent(buttonSignOut);
    }

    public HomePage checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not displayed ", isButtonSignOutPresent());
        return  this;
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
        if(!isButtonSignOutPresent()){
            loginPage.loginWithValidCred();
        }
        return this;
    }

    @Step
    public ProfilePage clickOnButtonProfile(){
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }

    @Step
    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    @Step
    public boolean isMessageFailCredentialPresent() {
        return isElementPresent(messageFailCredential);
    }

    @Step
    public  boolean isMessageFailUserNamePresent(){
        return isElementPresent(messageFailUserName);
    }

    @Step
    public boolean isMessageFailEmailPresent(){
        return isElementPresent(messageFailEmail);
    }

    @Step
    public  boolean isMessageFailPassWordPresent(){
        return isElementPresent(messageFailPassWord);
    }

}
