package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;


public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Lana230208 title of Post" + Util.getDateAndTimeFormated();
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
}
