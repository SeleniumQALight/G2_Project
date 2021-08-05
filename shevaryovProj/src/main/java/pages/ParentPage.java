package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;

    // конструктор
    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //Инициализация элементов описанных с помощью анотации @FindBy
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }

    // метод ввода текста в любое текстовое поле
    protected void enterTextToElement(WebElement webElement, String text) {
        try {
            // ожидание видимости элемента Инпут 15 сек
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted in element");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    //    клин на элементе
    protected void clickOnElement(WebElement webElement) {
        try {
            // ожидание кликабельности элемента 10 сек
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            // клик по элементу
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    //    проверка наличия элемента
    protected boolean isElementPresent(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is present");
            } else {
                logger.info("Element is not present");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not present");
            return false;
        }
    }

    // выбок строки в select-е по указанному тексту
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            // класс для работы с dropdown элементом. загружается все елементы
            Select select =  new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text+ "' was select in DropDown");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    // выбок строки в select-е по значению пар-ра value
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            // класс для работы с dropdown элементом. загружается все елементы
            Select select =  new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value+ "' was select in DropDown");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    // отображение ошибки
    private void writeErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        // безусловная остановка теста и вывод сообщение об ошибке
        Assert.fail("Can not work with element " + e);
    }
}
