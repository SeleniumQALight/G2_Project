package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

// класс по добавлению постов
public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Post by Oleg Shevaryov, created " + Util.getDateAndTimeFormatted();
    @Test
    public void createPost(){
        // логинимся на странице LoginPage,
        // проверяем правильность логина,
        // нажимаем кнопку создать пост,
        // проверяем что перешли на стрницу создния поста,
        // вводим текст в заголовок сообщения,
        // воодим текст в тело поста
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body text")
//                .selectTextInDropDownSelectValue("Частное сообщение")
                .selectValueInDropDownSelectValue("One Person")
                .clickOnSaveButton()
                ;

    }
}
