package pages;

import libs.Util;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
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
    protected final String baseURL = "https://qa-complex-app-for-testing.herokuapp.com";


    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        // Работает с вебэлемантами в чистом виде
//        PageFactory.initElements(webDriver, this);
        // Для работы с элементами Яндекса
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver)),this);
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }

    abstract String getRelativeURL();

    protected void checkURL() {
        Assert.assertEquals("Invalid page", baseURL + getRelativeURL(), webDriver.getCurrentUrl());
    }

    protected void checkURLWithPattern() {
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString(baseURL + getRelativeURL()));
    }

    protected void enterTextToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted in element" + getElementName(webElement));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = " '" + ((TypifiedElement)webElement).getName() + "' ";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + " Element was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void selectTextInDD(WebElement dropdown, String text) {
        try {
            Select select = new Select(dropdown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in DropDown " + getElementName(dropdown));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropdown (WebElement dropdown, String text) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(dropdown));
            dropdown.click();
            logger.info("Dropdown was clicked");
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(dropdown));
            dropdown.findElement(By.xpath(".//*[contains(text(),'" + text + "')]")).click();
            logger.info("Clicked on '" + text + "' in DD");
            Util.waitABit(1);
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void checkCheckBox(WebElement checkbox, String status) {
        try {
            if (status.equals("check")) {
                if (!checkbox.isSelected()) {
                    clickOnElement(checkbox);
                    logger.info("Checkbox was checked");
                } else {
                    logger.info("Not clicked, checkbox was checked already!");
                }
            } else if (status.equals("uncheck")) {
                if (checkbox.isSelected()) {
                    clickOnElement(checkbox);
                    logger.info("Checkbox was unchecked");
                } else {
                    logger.info("Not clicked, checkbox was unchecked already!");
                }
            } else {
                logger.info("Wrong checkbox status! Status can be 'check' or 'uncheck'.");
            }
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void selectValueInDD(WebElement dropdown, String value) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown" + getElementName(dropdown));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private void writeErrorAndStopTest(Exception e) {
        logger.error("Can't work with element " + e);
        Assert.fail("Can't work with element " + e);
    }

    protected boolean isElementPresent(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + "Element is present");
            } else {
                logger.info(getElementName(webElement) + "Element isn't present");
            }
            return state;
        } catch (Exception e) {
            logger.info(getElementName(webElement) + "Element is not present");
            return false;
        }
    }
}
