package pages;

import static org.hamcrest.CoreMatchers.containsString;

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

public abstract class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;
    protected final String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
//        PageFactory.initElements(webDriver, this);
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }

    abstract String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("Invalid page "
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
                           );
    }

    protected void checkUrlWithPattern(){
        Assert.assertThat("Invalid page ",
                webDriver.getCurrentUrl(),
                containsString(baseUrl + getRelativeUrl()));
    }

    protected void enterTextToElement(WebElement webElement, String text){
        try{
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted in element " + getElementName(webElement));
        }catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement){
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) +" Element was click");
        }catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName +" Element was click");
        }catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }

    protected boolean isElementPresent(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element present ");
            } else {
                logger.info(getElementName(webElement) + " Element is not present");
            }
            return state;
        }catch (Exception e){
            logger.info(getElementName(webElement) + "Element is not present");
            return false;
        }
    }

    protected void selectTextInDD(WebElement dropDown, String text){
        try{
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in DropDown " + getElementName(dropDown));
        }catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }

    protected void selectValueInDD(WebElement dropDown, String value){
        try{
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown " + getElementName(dropDown));
        }catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }

    private void writeErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
