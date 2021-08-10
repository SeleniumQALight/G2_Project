package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends ParentPage {
    String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private WebElement successPostDeleteElement;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/profile";
    }

    public ProfilePage checkisRedirectToProfilePage(){
        checkURLWithPattern();
        return this;
    }

    public ProfilePage checkIsPostWasAdded(String post_title) {
//        список элементов с нашими постами
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title)));
//        должно быть найдено только одно сообщение
        Assert.assertEquals("Number of posts with title " + post_title, 1, postList.size());

        return this;
    }

    // удаление поста на странице профиля
    public ProfilePage deletePostWithTitleWhilePresent(String post_title) {
        List<WebElement> listOfPosts = webDriver.findElements(
                By.xpath(String.format(postTitleLocator, post_title)));
        int counter = 0;

        // counter < 100 - защита от зацикливания
        while (!listOfPosts.isEmpty() && counter < 100) {
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, post_title))));
            // переходим на страницу single post page
            new PostPage(webDriver)
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletePostMessagePresent();
            // обновляем список постов
            listOfPosts = webDriver.findElements(
                    By.xpath(String.format(postTitleLocator, post_title)));
            counter++;
        }
        return this;
    }

    public ProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Element is not present", isElementPresent(successPostDeleteElement));
        return this;
    }
}
