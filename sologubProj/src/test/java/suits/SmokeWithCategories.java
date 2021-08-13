package suits;

import categories.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
import loginTest.RegistrationTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTestWithPageObject.class
})

public class SmokeWithCategories {

}
