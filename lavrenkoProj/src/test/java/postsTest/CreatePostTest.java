package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Illia Post" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Body test";

    @Test
    public void createPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoBody("POST BODY")
                .selectTextInDDSelectValue("Частное сообщение")
                .selectValueInDDSelectValue("One Person")
                .clickOnSaveButton()
                .checkIsButtonDeletePresent()
                .checkIsSuccesMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnTheProfileButton()
                .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage.openHomepage()
                .checkIsButtonSignOutVisible()
                .clickOnTheProfileButton()
                .deletePostWithTitleWhilePresent(POST_TITLE)

        ;
    }
}

