package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage {

    @FindBy(xpath = ".//a[@href='/profile/auto']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//a[@href='/profile/auto/followers']")
    private WebElement tabFollowers;

    // --------------------------------------------------------------------------------------------------
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }


    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return this;
    }

    public MyProfilePage checkIsFollowerPresent() {
        Assert.assertTrue("Followers tab isn't present", isElementPresent(tabFollowers));
        return this;
    }

    public void checkCreatedPost(String POST_TITLE) {
        String currentTitlePost = POST_TITLE;
        WebElement createPostTitle = webDriver.findElement(By.xpath(".//strong[text()='" + currentTitlePost + "']"));
        Assert.assertTrue("Created post isn't present in list", isElementPresent(createPostTitle));
    }
}
