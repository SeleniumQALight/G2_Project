package suits;

import loginTest.LoginTestWithPageObject;
import loginTest.RegistrationTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTestWithPageObject.class
})

public class Smoke {

}
