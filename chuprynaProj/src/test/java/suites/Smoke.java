package suites;

import loginTest.LoginTestWithPageObject;
import registrationTest.RegistrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTest.class
})
public class Smoke {
}
