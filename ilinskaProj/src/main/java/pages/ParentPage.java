package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
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

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;

public  abstract class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10,webDriverWait15;
    public static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class);

    protected final String baseUrl = configProperties.base_url();

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
//        PageFactory.initElements(webDriver, this);
        webDriverWait10=new WebDriverWait(webDriver,10);
        webDriverWait15=new WebDriverWait(webDriver,15);
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);// иметь возможность чтобі работать с елементами яндексс
    }
    protected void checkUrlWithPattern() {
        Assert.assertThat("Invalid page",
                webDriver.getCurrentUrl(),
                containsString(baseUrl + getRelativeUrl()));
    }
    abstract String getRelativeUrl();
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
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
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
    protected void selectTexttoInDD(WebElement dropDown,String text){
        try{
            Select select =new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(" "+text+"' was selected in Drop down menu");

        }catch (Exception e){
            writeErrorAndStopTest(e);
        }

    }
    protected void selectValuetoInDD(WebElement dropDown,String value){
        try{
            Select select =new Select(dropDown);
            select.selectByValue(value);
            logger.info(" "+value+"' was selected in Drop down menu");

        }catch (Exception e){
            writeErrorAndStopTest(e);
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

