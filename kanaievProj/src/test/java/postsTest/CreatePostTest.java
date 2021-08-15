package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    private final String POST_TITLE = "Pasha newbie title-" + Util.getDateAndTimeFormatted();

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCred()
                .checkIsRedirectOnHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body text")
//                .selectTextInDDSelectValue("Частное сообщение")
//                .selectValueInDDSelectValue("One Person")
                .selectTextInDropDownByClick("Частное сообщение")
                .clickCheckBoxToSetState("check")
                .clickButtonSaveNewPost()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonProfile()
                .checkIsRedirectOnProfilePage()
                .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .checkIsRedirectOnHomePage()
                .clickOnButtonProfile()
                .checkIsRedirectOnProfilePage()
                .deletePostWithTitleWhilePresent(POST_TITLE)
        ;
    }
}
