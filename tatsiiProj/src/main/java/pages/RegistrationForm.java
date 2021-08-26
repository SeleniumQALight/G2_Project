package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegistrationForm extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegister;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegister;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegister;

    @FindBy(xpath = "//div[contains(text(),'Username must be at least 3 characters.')]")
    private WebElement labelMessageShortUsername;

    @FindBy(xpath = "//div[contains(text(),'You must provide a valid email address.')]")
    private WebElement labelMessageValidEmail;

    @FindBy(xpath = "//div[contains(text(),'Password must be at least 12 characters.')]")
    private WebElement labelMessagePassword;

    public RegistrationForm(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openRegistrationForm() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Registration Form was opened");
        } catch (Exception e) {
            logger.error("Can't work with Registration Form" + e);
            Assert.fail("Can't work with Registration Form");
        }
    }

    public void enterLoginInSignUp(String login) {
        enterTextToElement(inputUserNameRegister, login);
    }

    public void enterEmailInSignUp(String email) {
        enterTextToElement(inputEmailRegister, email);
    }

    public void enterPasswordInSignUp(String password) {
        enterTextToElement(inputPasswordRegister, password);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public List<String> getActualErrorMessages(List<String> errorItems) {
        List<String> actualErrorMessages = new ArrayList<>();

        for (String error : errorItems) {
            WebElement label = webDriver.findElement(By.xpath
                    (String.format("//div[contains(text(),'%s')]", error)));
            if (isElementPresent(label)) {
                actualErrorMessages.add(error);
            }
        }
        return actualErrorMessages;
    }

    public boolean checkErrors(String errors) {
        List<String> errorItems = Arrays.asList(errors.split(";"));
        List<String> actualErrors = getActualErrorMessages(errorItems);
        return errorItems.size() == actualErrors.size();
    }

    public boolean isLabelMessageShortUsernamePresent() {
        return isElementPresent(labelMessageShortUsername);
    }

    public boolean isLabelMessageValidEmailPresent() {
        return isElementPresent(labelMessageValidEmail);
    }

    public boolean isLabelMessagePasswordPresent() {
        return isElementPresent(labelMessagePassword);
    }

    public void fillRegistrationFormAndSubmit(String login, String email, String password) {
        openRegistrationForm();
        enterLoginInSignUp(login);
        enterEmailInSignUp(email);
        enterPasswordInSignUp(password);
        clickOnButtonSignUp();
    }
}
//    public RegistrationForm enterLoginInRegistration(String login) {
//        enterTextToElement(inputLoginRegistration, login);
//        return this;
//    }
//
//    public LoginPage enterPassWordRegistration(String passWord) {
//        enterTextToElement(inputPassWordRegistration, passWord);
//        return this;
//    }
//
//    public LoginPage enterEmailInRegistration(String email) {
//        enterTextToElement(inputEmailInRegistration, email);
//        return this;
//    }
//
//    public void checkErrorMessages(String expectedErrors) {
//        String[] errorsArray = expectedErrors.split("'");
//        webDriverWait10.withMessage("Number of Messages")
//                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator), errorsArray.length));
////     Assert.assertEquals(actualListOfErrors.size(), errorsArray.length);
//
//        SoftAssertions softAssertions = new SoftAssertions();
//        ArrayList<String> actualTextFromErrors = new ArrayList<>();
//        for (WebElement element: actualListOfErrors) {
//            actualTextFromErrors.add(element.getText());
//        }
//        for (int i = 0; i < errorsArray.length; i++) {
//            softAssertions.assertThat(errorsArray[i]).isInactualTextFromErrors);
//
//        }
//
//        softAssertions.assertAll();
//
//    }
//}

