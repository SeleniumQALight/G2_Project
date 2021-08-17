package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage{

//    кнопка Sign Out
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;
//    кнопка Create Post
    @FindBy(xpath = ".//a[text()='Create Post']")
    private Button buttonCreatePost;
    //    кнопка профиля
    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonProfile;

    //    конструктор
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public HomePage chekIsRedirectOnHomePage(){
        checkURL();
        checkIsButtonSignOutVisible();
        return this;
    }

    //    наличие кнопки SignOut
    public boolean isButtonSignOutPresent(){
        return isElementPresent(buttonSignOut);
    }

    public HomePage checkIsButtonSignOutVisible() {
        //в методах check... всегда должен присутствовать assert
        Assert.assertTrue("Button Sign Out is not displayed", isButtonSignOutPresent());
        //this - верни эту же страницу(аналог "new HomePage(webDriver)")
        return this;
    }
    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutPresent()){
            loginPage.loginWithValidCred();
        }

        return this;
    }

    public ProfilePage clickOnButtonProfile(){
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }

}
