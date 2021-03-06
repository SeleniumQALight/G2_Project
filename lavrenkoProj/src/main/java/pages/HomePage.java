package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttSignOut;

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
    public HomePage checkIsRedirectOnHomePage(){
        checkUrl();
        checkIsButtonSignOutVisible();
        return this;
    }
    public boolean isButtonSignOutPresent() {
        return isElementPresent(buttSignOut);
    }

    public HomePage checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not displayed", isButtonSignOutPresent());
//        return new HomePage(webDriver);
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public HomePage openHomepage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutPresent()) {
            loginPage.loginWithValidCred();
        }

        return this;
    }

    public ProfilePage clickOnTheProfileButton() {
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }


}
