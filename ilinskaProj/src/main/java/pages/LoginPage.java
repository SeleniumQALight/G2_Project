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
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Сannot work with LoginPage"+ e);
            Assert.fail("Сannot work with LoginPage");
        }
    }

    public void enterLoginIn(String login) {
    enterTextToElement(inputLogin,login);

        }

    public void enterPasswwordInSign(String password) {
        enterTextToElement(inputPassword,password);

    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);

    }
    public  void fillinLoginFormandSubmit( String login,String password){
        openLoginPage();
        enterLoginIn(login);
        enterPasswwordInSign(password);
        clickOnButtonSignIn();
    }
    public HomePage loginWithValidCred(){
        fillinLoginFormandSubmit(TestData.VALIG_LOGin,TestData.VALID_PASSWORd);
        return new HomePage(webDriver);
    }
}


