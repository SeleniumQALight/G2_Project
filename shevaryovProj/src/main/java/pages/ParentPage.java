package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    // конструктор
    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //Инициализация элементов описанных с помощью анотации @FindBy
        PageFactory.initElements(webDriver, this);
    }

    // метод ввода текста в любое текстовое поле
    protected void enterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted in element");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

//    клин на элементе
    protected void clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }
//    проверка наличия элемента
    protected boolean isElementPresent(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            if (state){
                logger.info("Element is present");
            } else{
                logger.info("Element is not present");
            }
            return state;
        } catch (Exception e){
            logger.info("Element is not present");
            return false;
        }
    }

    // отображение ошибки
    private void writeErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        // безусловная остановка теста и вывод сообщение об ошибке
        Assert.fail("Can not work with element " + e);
    }
}
