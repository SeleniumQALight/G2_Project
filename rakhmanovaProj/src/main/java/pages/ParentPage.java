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

public abstract class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class);

    protected final String baseUrl = configProperties.base_url();
            //"https://qa-complex-app-for-testing.herokuapp.com";

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        // PageFactory.initElements(webDriver, this); --- it's working with WebElemnts only
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
        webDriverWait10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DFFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }

    abstract String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );
    }

    protected void checkUrlWithPattern(){
        Assert.assertThat("Invalid page ",
                webDriver.getCurrentUrl(),
                containsString(baseUrl + getRelativeUrl()));
    }


    protected void enterTextToElement(WebElement webElement, String text) {
        try{
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted in element" + getElementName(webElement));
        }catch (Exception e){
            writeErrorAndStopTest(e);
        }

    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if(webElement instanceof TypifiedElement){
            elementName = " '" + ((TypifiedElement)webElement).getName() + "' ";
        }
        return elementName;
    }


    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + " Element was clicked");
        }catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " Element was clicked");
        }catch (Exception e){
            writeErrorAndStopTest(e);
        }
    }

    protected boolean isElementPresent(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is present ");
            } else {
                logger.info(getElementName(webElement) + " Element is not present ");
            }
            return state;
        }catch (Exception e) {
            logger.info(getElementName(webElement) + " Element is not present");
            return false;
        }
    }


    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown " + getElementName(dropDown));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }


    protected void selectTextInDropDown(WebElement dropDown, String text){
         try{
             Select select = new Select(dropDown);
             select.selectByVisibleText(text);
             logger.info("'" + text + "' was selected in DropDown" + getElementName(dropDown));
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

    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }
    }

    public void userOpensNewTab() {
        ((JavascriptExecutor)webDriver).executeScript("window.open()");        // put a javascript command into the brackets
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

    // WebElement element = driver.findElement(By.id("gbqfd"));
//    JavascriptExecutor executor = (JavascriptExecutor)driver;
//    executor.executeScript("arguments[0].click();", element);
//    JavascriptExecutor jse = (JavascriptExecutor)driver;
//    jse.executeScript("document.getElementById('gbqfb').click();");


    // String downloadFilepath = "/path/to/download";
//HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//chromePrefs.put("profile.default_content_settings.popups", 0);
//chromePrefs.put("download.default_directory", downloadFilepath);
//ChromeOptions options = new ChromeOptions();
//options.setExperimentalOption("prefs", chromePrefs);
//DesiredCapabilities cap = DesiredCapabilities.chrome();
//cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//cap.setCapability(ChromeOptions.CAPABILITY, options);
//WebDriver driver = new ChromeDriver(cap);

    // new Actions(driver).moveToElement(element).perform();

  //  JavascriptExecutor jse = (JavascriptExecutor) driver;
  // jse.executeScript("document.getElementById('elementid').focus();");

    private void writeErrorAndStopTest(Exception e) {
        logger.error("Cannot work with an element " + e);
        Assert.fail("Cannot work with an element " + e);
    }

}
