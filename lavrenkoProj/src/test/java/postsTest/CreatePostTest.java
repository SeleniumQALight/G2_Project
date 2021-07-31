package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Illia title" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Body test";
    @Test
    public void createPost(){
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoBody("POST BODY")
        .clickOnSaveButton()
                ;
    }
}
