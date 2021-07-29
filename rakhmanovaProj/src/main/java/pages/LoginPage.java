package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("A Login page was opened.");
        } catch (Exception e) {
            logger.error("Cannot work with a login Page" + e);
            Assert.fail("Cannot work with a login Page");
        }
    }

    public void enterLoginInSignIn(String login) {
//        try{
//            //WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted in the SignIn input login.");
//        } catch (Exception e) {
//            logger.error("Cannot work with an element" + e);
//            Assert.fail("Cannot work with an element" + e);
//        }
        enterTextToElement(inputLogin, login);
    }

    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignIn() { clickOnElement(buttonSignIn); }

        public void fillLoginFormAndSubmit (String login, String password){
            openLoginPage();
            enterLoginInSignIn(login);
            enterPasswordInSignIn(password);
            clickOnButtonSignIn();
        }

        public HomePage loginWithValidCred(){
            fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
            return new HomePage(webDriver);
        }
    }
