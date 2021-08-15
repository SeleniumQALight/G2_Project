package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;


public class LoginPage extends ParentPage{
//    xpath к инпуту с именем пользователя
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private TextInput inputLogin;

//    xpath к инпуту с паролем
    @FindBy(xpath = ".//input[@placeholder='Password']")
    //можно назначать имя элемента
    @Name("Input PassWord")
    private TextInput inputPassWord;

//    xpath к кнопке Залогинится
    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(id = "username-register")
    private TextInput inputLoginRegistration;

    @FindBy(id = "email-register")
    private TextInput inputEmailRegistration;

    @FindBy(id = "password-register")
    private TextInput inputPassWordRegistration;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button buttonSignUp;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> actualListOfErrors;

    final String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    //    конструктор
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
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


    public LoginPage enterLoginInRegistration(String login) {
        enterTextToElement(inputLoginRegistration, login);
        return this;
    }

    public LoginPage enterEmailInRegistration(String email) {
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPassWordRegistration(String passWord) {
        enterTextToElement(inputPassWordRegistration, passWord);
        return this;
    }

    public void checkErrorsMessage(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(";");
        // ждём когда все элементы появятся
        webDriverWait10.withMessage("Number of Messages ")
        .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator), errorsArray.length));

        // Soft Assertion
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();

        for (WebElement element: actualListOfErrors ) {
            actualTextFromErrors.add(element.getText());
        }
        for (int i = 0; i < errorsArray.length; i++) {
            // проверки Soft Assertion
            softAssertions.assertThat(errorsArray[i]).isIn(actualTextFromErrors);
        }
        // срабатывают все проверки
        softAssertions.assertAll();

    }
}
