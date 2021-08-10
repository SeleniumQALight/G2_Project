package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private WebElement buttonProfile;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

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

    public HomePage checkIsRedirectOnHomePage(){
        checkUrl();
        checkIsButtonSignOutVisible();
        return this;
    }

    public boolean isButtonSignOutPresent(){
        return isElementPresent(buttonSignOut);
    }

    public HomePage checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not displayed ", isButtonSignOutPresent());
        return  this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!isButtonSignOutPresent()){
            loginPage.loginWithValidCred();
        }
        return this;
    }

    public ProfilePage clickOnButtonProfile(){
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isMessageFailCredentialPresent() {
        return isElementPresent(messageFailCredential);
    }

    public  boolean isMessageFailUserNamePresent(){
        return isElementPresent(messageFailUserName);
    }

    public boolean isMessageFailEmailPresent(){
        return isElementPresent(messageFailEmail);
    }

    public  boolean isMessageFailPassWordPresent(){
        return isElementPresent(messageFailPassWord);
    }

}
