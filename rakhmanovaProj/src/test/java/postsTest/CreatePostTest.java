package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Tetiana title of post" + Util.getDateAndTimeFormatted();

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Post text")
                .clickOnSaveButton()
                ;

    }

}