package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Yevhen Vorkov title of Post" + Util.getDateAndTimeFormated();
    final String POST_BODY = "Yevhen Vorkov body of Post";

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCred()
                .checkIsRedirectOnHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .selectValueInDDSelectValue("One Person")
                .clickOnSaveNewPost()
                .checkRedirectWithParam()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsDeletePostButtonPresent()
                .clickOnButtonMyProfile()
                .checkIsRedirectionProfilePage()
                .checkIsPostWasAdded(POST_TITLE)
                ;
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .checkIsRedirectOnHomePage()
                .clickOnButtonProfile()
                .checkIsRedirectionProfilePage()
                .deletePostWithTitleWhilePresent(POST_TITLE);
    }
}
