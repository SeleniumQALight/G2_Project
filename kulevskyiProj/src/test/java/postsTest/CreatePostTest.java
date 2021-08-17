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
               .checkIsButtonSingOutVisible()
               .clickOnButtonCreatePost()
               .checkIsInputTitlePresent()
               .enterTextIntoInputTitle(POST_TITLE)
               .enterTextIntoInputBody("Body text")
//               .selectTextInDDSelectValue("Частное сообщение")
               .selectValueInDDSelectValue("One Person")
               .clickOnSaveButton()
               .checkIsButtonDeletePresent()
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
            .checkIsButtonSingOutVisible()
                .clickOnButtonProfile()
                .deletePostWithTitleWhilePresent(POST_TITLE)
                ;
    }

}

