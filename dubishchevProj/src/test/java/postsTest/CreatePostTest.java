package postsTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Dubishchev title " + Util.getDateAndTimeFormatted();

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCred()
                .checkIsRedirectOnHomePage()
//                .checkIsButtonSignOutVisible() //moved to checkIsRedirectOnHomePage
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .checkIsTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Some body text")
                //.selectTextInDDSelectValue("Частное сообщение")
                .selectValueInDDSelectValue("One Person")
                .clickOnSaveButton()
                .checkIsRedirectOnPostPage()
                //.checkIsButtonDeletePresent() //moved to checkIsRedirectOnPostPage
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnProfileButton()
                .checkIsRedirectOnProfilePage()
                .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @Test
    @Parameters({
     "Частное сообщение",
     "Some text"
    })
    @TestCaseName("Select text from dropdown and click : textToClick = {0}")
    public void selectFromDropDown(String textToClick){
        loginPage.
                loginWithValidCred().
                clickOnButtonCreatePost().
                selectTextInDropDownByClick(textToClick);
    }

    @After
    public void postDelete() {
        homePage.openHomePage()
                .checkIsRedirectOnHomePage()
                .clickOnProfileButton()
                .checkIsRedirectOnProfilePage()
                .deletePostWithTitleWhilePresent(POST_TITLE)
        ;
    }


}
