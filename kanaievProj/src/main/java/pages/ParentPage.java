package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;

public abstract class ParentPage {
    protected final String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
        webDriverWait10 = new WebDriverWait(this.webDriver, 10);
        webDriverWait15 = new WebDriverWait(this.webDriver, 15);
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page ",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        Assert.assertThat("Invalid page",
                webDriver.getCurrentUrl(),
                containsString(baseUrl + getRelativeUrl()));
    }

    protected void enterTextToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted in element ");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected boolean isElementPresent(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element present");
            } else {
                logger.info("Element is not present");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not present");
            return false;
        }
    }

    protected void selectTextInDD(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in DropDown");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void selectValueInDD(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void setCheckBoxStateTo(WebElement webElement, String checkBoxState) {
        boolean isSelectExpected = true;
        if (checkBoxState.equalsIgnoreCase("check")) {
            isSelectExpected = true;
        } else if (checkBoxState.equalsIgnoreCase("uncheck")) {
            isSelectExpected = false;
        } else {
            writeErrorAndStopTest(new Exception("Wrong string was set to method \"setCheckBoxStateTo\""));
        }
        try {
            if (!(webElement.isSelected() == isSelectExpected)) {
                clickOnElement(webElement);
            } else {
                logger.info("CheckBox already in expected state \"" + checkBoxState + "\"");
            }
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private void writeErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
