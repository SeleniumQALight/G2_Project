package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;


public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Lana230208 title of Post" + Util.getDateAndTimeFormated();
    @Test
    public void createPost(){
        loginPage
                .loginWithValidCred()
                .checkIsRedirectOnHomePage()
//             .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
             .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body text")
//                .selectTextInDDSelectValue("Частное сообщение")
                .selectValueInDDSelectValue("One Person")
                .selectTextInDropDownByClick("Group Message")
                .checkCheckBox(true)
                .clickOnSaveButton()

             .checkIsRedirectToPostPage()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonProfile()
                .checkIsRedirectToProfilePage()
            .checkIsPostWasAdded(POST_TITLE)
                ;
    }
    @After
    public void deletePost(){
        homePage
                .openHomePage()
            .checkIsRedirectOnHomePage()
                .clickOnButtonProfile()
                .checkIsRedirectToProfilePage()
                .deletePostWithTitleWhilePresent(POST_TITLE)
                ;
    }
}
