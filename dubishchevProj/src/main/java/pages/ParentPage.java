package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    //    protected final String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";
    protected final String baseUrl = configProperties.base_url();  //base url from config properties


    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
//        PageFactory.initElements(webDriver, this);  work with web elements, but we need with yandex elements
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver)), this); //for use yandex elements
        webDriverWait10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DFFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page ", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        Assert.assertThat("Ivalid page ", webDriver.getCurrentUrl(), containsString(baseUrl + getRelativeUrl()));
    }

    protected void enterTextToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputed in element " + getElementName(webElement));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
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


    protected boolean isElementPresent(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element present");
            } else {
                logger.info(getElementName(webElement) + " Element is not present");
            }
            return state;
        } catch (Exception e) {
            logger.info(getElementName(webElement) + "Element is not present");
            return false;
        }
    }

    private void writeErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected String getElementText(WebElement webElement) {
        try {
            return webElement.getText();
        } catch (Exception e) {
            logger.info(getElementName(webElement) + "Element is not present");
            return "No text was found";
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in dropdown" + getElementName(dropDown));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in dropdown" + getElementName(dropDown));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByClick(WebElement dropDown, String textToClick) {
        try {
            dropDown.click();
            logger.info(getElementName(dropDown) + " Element was clicked");
            webDriver.findElement(By.xpath(String.format(".//option[text()='%s']", textToClick))).click();
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void setCheckbox(WebElement webElement, boolean toCheck) {
        try {
            if (!webElement.isSelected() && toCheck) {
                webElement.click();
                logger.info(getElementName(webElement) + " was checked");
            } else if (!webElement.isSelected() && !toCheck) {
                logger.info(getElementName(webElement) + " don't need to check");
            } else if (webElement.isSelected() && toCheck) {
                logger.info(getElementName(webElement) + " already checked");
            } else if (webElement.isSelected() && !toCheck) {
                webElement.click();
                logger.info(getElementName(webElement) + " was unchecked");
            } else {
                logger.info("Something unusual..Nothing ot do..");
            }
        } catch (
                Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void setCheckbox(WebElement webElement, String actionWithCheckBox) {
        if (actionWithCheckBox.equals("Check") || actionWithCheckBox.equals("Uncheck")) {
            try {
                if (!webElement.isSelected() && actionWithCheckBox.equals("Check")) {
                    webElement.click();
                    logger.info(getElementName(webElement) + " was checked");
                } else if (!webElement.isSelected() && actionWithCheckBox.equals("Uncheck")) {
                    logger.info(getElementName(webElement) + " don't need to check");
                } else if (webElement.isSelected() && actionWithCheckBox.equals("Check")) {
                    logger.info(getElementName(webElement) + " already checked");
                } else if (webElement.isSelected() && actionWithCheckBox.equals("Uncheck")) {
                    webElement.click();
                    logger.info(getElementName(webElement) + " was unchecked");
                } else {
                    logger.info("Something unusual..Nothing to do..");
                }
            } catch (Exception e) {
                writeErrorAndStopTest(e);
            }
        } else {
            logger.info("You don't choose right action with checkbox");
            Assert.fail();
        }
    }


    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }

    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();

        }
    }

//open and switch between tabs
//    public void userOpensNewTab() {
//        ((JavascriptExecutor)webDriver).executeScript("window.open()");
//        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
//        webDriver.switchTo().window(tabs.get(1));
//    }

}
