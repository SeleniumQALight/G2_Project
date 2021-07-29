package postTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest{
    final String POST_TITLE="Valeriia title of post"+ Util.getDateAndTimeFormatted();

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickonButtonCreatePost()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTexttoBodyTitle("Body text")
                .clickOnSaveButton()
                .checkIsButtonDeletePresent()
                .checkIsSucessMessagePresent()
                .checkTextinSuccessMessage("New post successfully created.")
                .clickonProfile()
                 .checkisPostwasAdded(POST_TITLE)
                ;

    }

    }
