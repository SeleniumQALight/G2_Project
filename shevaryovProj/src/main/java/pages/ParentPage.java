package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static org.hamcrest.CoreMatchers.containsString;

public abstract class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;
    // подключаем конфигурационный файл
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    // получаем значение baseURL из конфига
    protected final String baseURL = configProperties.base_url();
//    protected final String baseURL = "https://qa-complex-app-for-testing.herokuapp.com";

    // конструктор
    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //Инициализация элементов описанных с помощью @FindBy. Работает только с WebElement
//        PageFactory.initElements(webDriver, this);
        // Инициализация WebElement и элементов Яндекса. То же что и строка выше, но с Яндексом
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                , this);
        webDriverWait10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DFFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }

    // абстрактный метод, возвращающий url
    abstract String getRelativeURL();

    protected void checkURL() {
        Assert.assertEquals("Invalid page"
                , baseURL + getRelativeURL()
                , webDriver.getCurrentUrl());
    }

    protected void checkURLWithPattern() {
        Assert.assertThat("Invalid page",
                webDriver.getCurrentUrl(),
                containsString(baseURL + getRelativeURL()));
    }

    // метод ввода текста в любое текстовое поле
    protected void enterTextToElement(WebElement webElement, String text) {
        try {
            // ожидание видимости элемента Инпут 15 сек
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted in element " + getElementName(webElement));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        // проверка наследует ли элемент определённый класс
        if (webElement instanceof TypifiedElement) {
            // явное приведение типов, яндекса к webElement
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;
    }

    //    клик на элементе
    protected void clickOnElement(WebElement webElement) {
        try {
            // ожидание кликабельности элемента 10 сек
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            // клик по элементу
            webElement.click();
            logger.info(getElementName(webElement) + " Element was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    //    клик на элементе с указанием имени элемента
    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            // ожидание кликабельности элемента 10 сек
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            // клик по элементу
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    //    проверка наличия элемента
    protected boolean isElementPresent(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is present");
            } else {
                logger.info(getElementName(webElement) + " Element is not present");
            }
            return state;
        } catch (Exception e) {
            logger.info(getElementName(webElement) + "Element is not present");
            return false;
        }
    }

    // выбок строки в select-е по указанному тексту
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            // класс для работы с dropdown элементом. загружается все елементы
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was select in DropDown " + getElementName(dropDown));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    // выбок строки в select-е по значению пар-ра value
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            // класс для работы с dropdown элементом. загружается все елементы
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was select in DropDown " + getElementName(dropDown));
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
