package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class PostPage extends ParentPage{
//    кнопка удаления
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private Button buttonDelete;

    // элемент с сообщением об успешном сохранении
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private TextBlock successMessageElement;

    //    кнопка профиля
    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private Button buttonProfile;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/";
    }

    public PostPage checkIsRedirectToPostPage(){
        checkURLWithPattern();
        checkIsButtonDeletePresent();
        return this;
    }
    public PostPage checkIsButtonDeletePresent(){
//        наличие кнопки удалить
        Assert.assertTrue("Button Delete is not present", isElementPresent(buttonDelete));
        return this;
    }

    public PostPage checkSuccessMessagePresent(){
//        проверка наличия текст сообщения
        Assert.assertTrue("Success message is not present", isElementPresent(successMessageElement));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String text){
//        проверка наличия элемента с сообщением
        String actualText = successMessageElement.getText();
        Assert.assertEquals("Text is message", text, actualText);
        return this;
    }

    public ProfilePage clickOnButtonProfile(){
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnDeleteButton() {
//        кликаем по кнопке удалить
        clickOnElement(buttonDelete);
        return new ProfilePage(webDriver);
    }
}
