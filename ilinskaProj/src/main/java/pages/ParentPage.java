package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
//        PageFactory.initElements(webDriver, this);
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                , this);// иметь возможность чтобі работать с елементами яндексс
    }

    protected void enterTextToElement(WebElement webElement, String text) {
        try {

            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "'was inputted in element" + getElementName(webElement));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = " '" + ((TypifiedElement) webElement).getName() + " '";
        }
        return elementName;

    }

    public void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info(getElementName(webElement) + " Element was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }

    }

    public void clickOnElement(WebElement webElement, String elementName) {
        try {
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }

    }

    protected boolean isElementPresent(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element state");
            } else {
                logger.info("Element is not present");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not present");
            return false;
        }
    }

    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();

        }
    }

    public void userOpensNewTab() {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();

        }

    }
        private void writeErrorAndStopTest (Exception e){
            logger.error("Сan not work with element" + e);
            Assert.fail("Сan not work with element" + e);
        }

    }

