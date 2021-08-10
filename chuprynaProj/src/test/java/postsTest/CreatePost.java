package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePost extends BaseTest {
    final String POST_TITLE = "Chupryna title of post " + Util.getDateAndTimeFormatted();

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCredentials()
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsRedirectedOnCreatePostPage()
                .checkIsInputTitlePresent()
                .enterTextIntoPostTitleInput(POST_TITLE)
                .enterTextIntoPostBodyInput("Body text")
//                .selectTextInDDSelectValue("Частное сообщение")
                .selectValueInDDSelectValue("One Person")
                .clickOnButtonSavePost()
                .checkIsButtonDeletePresent()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonProfile()
                .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnButtonProfile()
                .deletePostWithTitleWhilePresent(POST_TITLE)
        ;
    }
}
