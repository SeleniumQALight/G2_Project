package pages;

import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginPage extends ParentPage {
    //    xpath к инпуту с именем пользователя
    @FindBy(xpath = ".//input[@placeholder='Username']")

    private WebElement inputLogin;
    //    xpath к инпуту с паролем
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWord;

    //    xpath к кнопке Залогинится
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    //    xpath к инпуту имени пользователя для регистрации
    @FindBy(xpath = ".//input[@placeholder = 'Pick a username']")
    private WebElement inputRegistrationUserName;

    //    xpath регистрационная почта
    @FindBy(xpath = ".//input[@placeholder = 'you@example.com']")
    private WebElement inputRegistrationEMail;

    //    xpath регистрационный пароль
    @FindBy(xpath = ".//input[@placeholder = 'Create a password']")
    private WebElement inputRegistrationPassWord;

    //    xpath сообщение о неверном имени пользователя
    @FindBy(xpath = ".//div[contains(text(),'at least 3 characters')]")
    private WebElement errorMessageWrongUserName;

    //    xpath сообщение о неверном формате e-mail
    @FindBy(xpath = ".//div[contains(text(),'valid email address')]")
    private WebElement errorMessageWrongEMail;

    //    конструктор
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
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
    public void fillLoginFormAndSubmit(String login, String passWord) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPassWordInSignIn(passWord);
        clickOnButtonInSignIn();
    }

    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        // возвращаем новый объект HomePage
        return new HomePage(webDriver);
    }


    public void enterRegistrationUserName(String invalidLogin) {
        enterTextToElement(inputRegistrationUserName, invalidLogin);
    }

    public void enterRegistrationEMail(String invalidEmail) {
        enterTextToElement(inputRegistrationEMail, invalidEmail);
    }

    public void enterRegistrationPassWord(String validPassword) {
        enterTextToElement(inputRegistrationPassWord, validPassword);
    }

    public void checkErrors(String errorMessage) {
        List<String> errorList = new ArrayList<String>();
        errorList = Arrays.asList(errorMessage.split(";"));
        Util.waitABit(5);

        //if (errorList.size() == 2) {
            Assert.assertEquals("Error message is not present", errorList.get(0), errorMessageWrongUserName.getText());
            Assert.assertEquals("Error message is not present", errorList.get(1), errorMessageWrongEMail.getText());
        //}
    }
}
