package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProfilePage extends ParentPage{
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage checkIsPostWasAdded(String post_title) {
//        список элементов с указанным текстом
        List<WebElement> postList = webDriver.findElements(
                By.xpath(String.format(".//*[text()='%s']", post_title)));
//        добжно быть найдено только одно сообщение
        Assert.assertEquals("Number of posts with title " + post_title, 1, postList.size());

        return this;
    }
}
