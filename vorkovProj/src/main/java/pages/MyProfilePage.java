package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
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

    public MyProfilePage checkIsPostWasAdded(String post_title) {
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(".//*[text()='%s']", post_title)));
        Assert.assertEquals("Count of posts with title " + post_title,1,postList.size());
        return this;
    }

//    public MyProfilePage selectAddedPost(String post_title){
//        WebElement postList = webDriver.findElements(
//                By.xpath(String.format(".//*[text()='%s']", post_title)));
//        clickOnElement(postList);
//    }
}
