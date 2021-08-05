package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@placeholder = 'Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWord;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUseName;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEMail;

    @FindBy(xpath = ".//button[text() = 'Sign up for OurApp']")
    private WebElement buttonSignOut;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
    }

    public void enterLoginInSignIn(String login) {
//        try{
////            WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder = 'Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted in SignIn input login");
//        }catch(Exception e){
//            logger.error("Can not work with element" + e);
//            Assert.fail("Can not work with element");
//        }
        enterTextToElement(inputLogin, login);

    }

    public void enterPassWordInSignIn(String passWord) {
        enterTextToElement(inputPassWord, passWord);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }
    public void fillLoginFormAndSubmit(String login, String passWord){
        openLoginPage();
        enterLoginInSignIn(login);
        enterPassWordInSignIn(passWord);
        clickOnButtonSignIn();

    }

    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return  new HomePage(webDriver);

    }

    public HomePage loginWithInvalidCred(){
        fillLoginFormAndSubmit(TestData.INVALID_LOGIN, TestData.INVALID_PASSWORD);
        return  new HomePage(webDriver);
    }


    public void fillSignOutFormAndSubmit(String userName, String eMail, String passWord) {
        openLoginPage();
        enterUserNameInSignOut(userName);
        enterEMailInSignOut(eMail);
        enterPassWordInSignOut(passWord);
        clickOnButtonSignOut();
    }

    private void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }

    private void enterPassWordInSignOut(String passWord) {
        enterTextToElement(inputPassWord, passWord);
    }

    private void enterEMailInSignOut(String eMail) {
        enterTextToElement(inputEMail, eMail);
    }

    private void enterUserNameInSignOut(String userName) {
        enterTextToElement(inputUseName, userName);
    }
}
