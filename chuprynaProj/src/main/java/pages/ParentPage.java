package pages;

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
    protected final String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
//        Works only with raw WebElements:
//          PageFactory.initElements(webDriver, this); //initializing all elements described by FindBy
//        Works with Yandex HTML elements (as well as with raw WebElements)
//        - is an extension for the prev initialization:
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                , this);
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page url",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl()
        );
    }

    protected void checkUrlWithPattern() {
        Assert.assertThat("Invalid page url",
                webDriver.getCurrentUrl(),
                containsString(baseUrl + getRelativeUrl()));
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
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element " + getElementName(webElement) + "was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element " + elementName + " was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected boolean isElementPresent(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element " + getElementName(webElement) + " is displayed");
            } else {
                logger.info("Element " + getElementName(webElement) + " is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element " + getElementName(webElement) + " is not present");
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in DropDown " + getElementName(dropDown));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value) { //works faster than by text
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByClick(WebElement dropDown, String text) {
        try {
            clickOnElement(dropDown);
            WebElement selectOption = webDriver.findElement(By.xpath(String.format(".//option[text() = '%s']", text)));
            clickOnElement(selectOption, "Option '" + text + "' in " + getElementName(dropDown));
//            logger.info("'" + text + "' was selected in DropDown " +  + " by click");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected void selectStateInCheckbox(WebElement checkBox, String state) throws IllegalArgumentException {
        try {
            boolean stateBoolean = convertVerbalChBStateToBoolean(state);
            if (stateBoolean == checkBox.isSelected()) {
                logger.info("Checkbox " + getElementName(checkBox) + " is already in state: " + state);
            } else {
                clickOnElement(checkBox);
                logger.info("Checkbox " + getElementName(checkBox) + " was toggled to state: " + state);
            }
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private boolean convertVerbalChBStateToBoolean(String state) throws IllegalArgumentException {
        if (state.equalsIgnoreCase("check")) {
            return true;
        } else if (state.equalsIgnoreCase("uncheck")) {
            return false;
        } else {
            throw new IllegalArgumentException("Incorrect state is entered for checkbox");
        }
    }

    private void writeErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element:" + e.getMessage());
        Assert.fail("Cannot work with element:" + e);
    }
}
