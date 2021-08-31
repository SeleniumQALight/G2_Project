package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Denis title of Post " + Util.getDateAndTimeFormatted();
    @Test
    public void createPost(){
       loginPage
               .loginWithValidCred()
                 .checkIsRedirectOnHomePage()
//               .checkIsButtonSingOutVisible()
               .clickOnButtonCreatePost()
                  .checkIsRedirectOnCreatePostPage()
               .checkIsInputTitlePresent()
               .enterTextIntoInputTitle(POST_TITLE)
               .enterTextIntoInputBody("Body text")
//               .selectTextInDDSelectValue("Частное сообщение")
               .selectValueInDDSelectValue("One Person")
               .clickOnSaveButton()
                   .checkIsRedirectToPostge()
               .checkIsSuccessMassagePresent()
               .checkTextInSuccessMassage("New post successfully created.")
               .clickOnButtonProfile()
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

