package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Nivinskaya title of Post" + Util.getDateAndTimeFormated();
    final String POST_BODY = "Nivinskaya body of Post" + Util.getDateAndTimeFormated();

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCred()
                .checkIsRedirectOnHomePage()
//                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
//                .selectTextInDDSelectValue("Частное сообщение")
//                .selectValueInDDSelectValue("One Person")
                .selectTextInDDByClickSelectValue("Общедоступное")
                .clickOnSaveButton()
                .checkIsRedirectOnPostPage()
//                .chechIsButtonDeletePresent()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonProfile()
                .checkIsRedirectToProfilePage()
                .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage.openHomePage()
                .checkIsRedirectOnHomePage()
//                .checkIsButtonSignOutVisible()
                .clickOnButtonProfile()
                .checkIsRedirectToProfilePage()
                .deletePostWithTitleWhilePresent(POST_TITLE)
        ;
    }
}
