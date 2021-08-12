package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

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
