package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Chupryna title of post " + Util.getDateAndTimeFormatted();

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCredentials()
                .checkIsRedirectedOnHomePage()
//                .checkIsButtonSignOutVisible() -> place this check in checkIsRedirectedOnHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectedOnCreatePostPage()
                .checkIsInputTitlePresent()
                .enterTextIntoPostTitleInput(POST_TITLE)
                .enterTextIntoPostBodyInput("Body text")
//                .selectTextInDDSelectValue("Частное сообщение")
//                .selectValueInDDSelectValue("One Person")
                .selectTextInDDByClickSelectValue("Частное сообщение")
                .selectOptionInCheckboxUniquePost("check")
                .clickOnButtonSavePost()
//                .checkIsButtonDeletePresent()
                .checkIsRedirectedOnPostPage()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                  .clickOnButtonProfile()
                .checkIsRedirectedOnProfilePage()
                .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .checkIsRedirectedOnHomePage()
                  .clickOnButtonProfile()
                .checkIsRedirectedOnProfilePage()
                  .deletePostWithTitleWhilePresent(POST_TITLE)
        ;
    }
}
