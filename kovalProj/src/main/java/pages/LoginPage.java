package pages;

import io.qameta.allure.Step;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private TextInput inputlogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    @Name("Input Pass")
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register" )
    private TextInput inputLoginRegistration;

    @FindBy(id = "email-register")
    private TextInput inputEmailRegistration;

    @FindBy(id = "password-register")
    private  TextInput inputPasswordRegistration;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> actualListOfErrors;

    final String listErroorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage(){
        try{
            webDriver.get(baseUrl);
            logger.info("Login page was opened");

        }catch (Exception e){
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
    }
    @Step
    public void enterLoginInSignIn(String login) {
     //   try{
       //     WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
     //       inputlogin.clear();
     //       inputlogin.sendKeys(login);
    //        logger.info(login + "was inputted in SignIn input login");
     //   }catch(Exception e){
     //       logger.error("Can not work with element" + e);
     //       Assert.fail("Can not work with element" + e);
      //  }
        enterTextToElement(inputlogin, login);
    }
    @Step
    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }
    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }
    @Step
    public void fillingLoginFormAndSubmit(String login, String password){
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }
    @Step
    public HomePage loginWithValidCred(){
        fillingLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASS);
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage enterLoginInRegistration(String login) {
        enterTextToElement(inputLoginRegistration, login);
        return this;
    }
    @Step
    public LoginPage enterEmailInRegistration(String email) {
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }
    @Step
    public LoginPage enterPassWordRegistration(String passWord) {
        enterTextToElement(inputPasswordRegistration, passWord);
        return this;
    }
    @Step
    public void checkErrorsMessages(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of Messages").until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErroorsLocator), errorsArray.length));
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: actualListOfErrors){
            actualTextFromErrors.add(element.getText());
        }
        for (int i = 0; i < errorsArray.length; i++){
            softAssertions.assertThat(errorsArray[i]).isIn(actualTextFromErrors);
        }


        softAssertions.assertAll();
    }
}
