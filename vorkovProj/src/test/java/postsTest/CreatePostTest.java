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
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .checkIsInputTitleIsPresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
//                .selectTextInDDSelectValue("Частное сообщение")
                .selectValueInDDSelectValue("One Person")
                .clickOnSaveNewPost()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsDeletePostButtonPresent()
                .clickOnButtonMyProfile()
                .checkIsFollowerPresent()
                .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .checkIsButtonSignOutVisible().clickOnButtonProfile().deletePostWithTitleWhilePresent(POST_TITLE);
    }

    @Test
    public void testCheckBoxChecker() {
        loginPage
                .loginWithValidCred()
                .clickOnButtonCreatePost();
        Util.waitABit(1);
        createPostPage.uniquePostCheckbox("check");
        Util.waitABit(1);
        createPostPage.uniquePostCheckbox("check");
        Util.waitABit(1);
        createPostPage.uniquePostCheckbox("uncheck");
        Util.waitABit(1);
        createPostPage.uniquePostCheckbox("uncheck");
        Util.waitABit(1);
        createPostPage.uniquePostCheckbox("REcheck");
    }

    @Test
    public void testDDByClick() {
        loginPage
                .loginWithValidCred()
                .clickOnButtonCreatePost();
        Util.waitABit(1);
        createPostPage.selectTextInDDByClick("Сообщение для группы");
    }
}

