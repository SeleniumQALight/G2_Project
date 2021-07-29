package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends ParentPage{
//    xpath к инпуту с именем пользователя
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
//    xpath к инпуту с паролем
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWord;
//    xpath к кнопке Залогинится
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

//    конструктор
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com");
            logger.info("Login page was opened");
        } catch (Exception e){
            logger.error("Can not work with LoginPage " + e);
            Assert.fail("Can not work with LoginPage");
        }
    }

    public void enterLoginInSignIn(String login) {
//        Находим поле логина и заполняем логином
//        try {
//             создаем @FindBy вместо этой строки
//            WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted in SignIn input login");
//        }catch (Exception e){
//            logger.error("Can not work with element " + e);
//            Assert.fail("Can not work with element");
//        }
        enterTextToElement(inputLogin, login);
    }

    public void enterPassWordInSignIn(String passWord) {
        enterTextToElement(inputPassWord, passWord);
    }

    public void clickOnButtonInSignIn() {
        clickOnElement(buttonSignIn);
    }

    // схлопываем методы. открываем главную и логинимся на странице
    public void fillLoginFormAndSubmit(String login, String passWord){
        openLoginPage();
        enterLoginInSignIn(login);
        enterPassWordInSignIn(passWord);
        clickOnButtonInSignIn();
    }

    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        // возвращаем новый объект HomePage
        return new HomePage(webDriver);
    }
}
