package suits;

import categories.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTest.RegistrationTest;

//указываем что тест будем запускать с категориями
@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
// список классов с тестами для запуска
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTest.class
})
public class SmokeWithCategories {
}
