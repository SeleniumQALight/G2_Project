package postsTest;

import org.junit.After;
import org.junit.Test;

import baseTest.BaseTest;
import libs.Util;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Taras title of Post" + Util.getDateAndTimeFormatted();
    @Test
    public void createPost(){
        loginPage
                .loginWithValidCred()
             .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
             .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body text")
                .clickOnSaveButton()
             .checkIsButtonDeletePresent()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonProfile()
              .checkIsPostWasAdded(POST_TITLE)
                ;
    }
    @After
    public void deletePost(){
        homePage
                .openHomePage()
            .checkIsButtonSignOutVisible()
                .clickOnButtonProfile()
                .deletePostWithTitleWhilePresent(POST_TITLE)
                ;
    }
}
