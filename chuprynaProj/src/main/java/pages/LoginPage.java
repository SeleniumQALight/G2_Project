package pages;

import libs.TestData;
import org.junit.Assert;
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

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Cannot work with Login Page:" + e); // writes into log file
            Assert.fail("Cannot work with Login Page"); // to stop test in case of error; writes into reports
        }
    }

    public void enterLoginInSignInForm(String userName) {
//        try {
//            inputLogin.clear();
//            inputLogin.sendKeys(userName);
//            logger.info(userName + " was inputted in SignIn input Username");
//        } catch (Exception e){
//            logger.error("Cannot enter username in SingIn form:" + e);
//            Assert.fail("Cannot enter username in SingIn form");
//        }
        enterTextToElement(inputLogin, userName);
    }

    public void enterPasswordInSignInForm(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnSignInButton() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String userName, String password){
        openLoginPage();
        enterLoginInSignInForm(userName);
        enterPasswordInSignInForm(password);
        clickOnSignInButton();
    }

    public HomePage loginWithValidCredentials(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
}
