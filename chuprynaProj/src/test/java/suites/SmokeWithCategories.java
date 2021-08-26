package suites;

import categories.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
import registrationTest.RegistrationTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTest.class
})
public class SmokeWithCategories {
}
