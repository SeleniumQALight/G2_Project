package postTest;

import baseTest.BaseTest;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Hanna title of Post" + Util.getDateAndTimeFormatted();


    @Parameters({
            "Общедоступное, Check",
            "Частное сообщение, Uncheck",
            "Сообщение для группы, Check123"
    })
    @TestCaseName("Create post test : textToClickInDropDown = {0}, actionWithCheckbox = {1}")
    @Test
    public void createPost() {
        loginPage
                .loginWithValidCred()
//                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnPostPage()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body text")
//                .selectTextInDDSelectValue ("Частное сообщение")
           //     .selectValueInDDSelectValue("One Person")
                .selectTextInDropDownByClick("Частное сообщение")
                .clickCheckBoxToSetState("check")
                .clickOnSaveButton()

                .checkIsRedirectToPostPage()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonProfile()
                .checkIsRedirectToProfilePage()
                .checkIsPostWasAdded(POST_TITLE);
        ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .checkIsRedirectOnHonePage()
                .clickOnButtonProfile()
                .checkIsRedirectToProfilePage()
                .deletePostWithTitleWhilePresent(POST_TITLE)
        ;
    }
}
