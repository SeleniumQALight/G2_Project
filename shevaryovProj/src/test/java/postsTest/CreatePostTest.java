package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

// класс по добавлению постов
public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Post by Oleg Shevaryov, created " + Util.getDateAndTimeFormatted();
    @Test
    public void createPost(){
        // 1. логинимся на странице LoginPage,
        // 2. проверяем правильность логина,
        // 3. нажимаем кнопку создать пост,
        // 3.1.
        // 4. проверяем что перешли на стрницу создния поста,
        // 5. вводим текст в заголовок сообщения,
        // 6. воодим текст в тело поста
        // 7. кликаем по кнопке сохранить
        // 8. проверяем наличие кнопки Удалить
        // 9. проверяем наличие элемента для сообщения о сохранении поста
        // 10. проверяем наличие сообщения об успешном сохранении поста
        // 11. нажать на кнопку перехода в профиль
        // 12. проверка добавления поста по его названию
        loginPage
                .loginWithValidCred()
                .chekIsRedirectOnHomePage()
//             .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
             .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body text")
//                .selectTextInDropDownSelectValue("Частное сообщение")
                .selectValueInDropDownSelectValue("One Person")
                .clickOnSaveButton()
//             .checkIsButtonDeletePresent()
                .checkIsRedirectToPostPage()
                .checkSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonProfile()
                .checkisRedirectToProfilePage()
                .checkIsPostWasAdded(POST_TITLE)
                ;

    }
    @After
    public void deletePost(){
        homePage
                .openHomePage()
            .chekIsRedirectOnHomePage()
                .clickOnButtonProfile()
            .checkisRedirectToProfilePage()
                .deletePostWithTitleWhilePresent(POST_TITLE)

                ;
    }
}
