package suits;

import apiTests.ApiTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import loginTest.LoginTestWithPageObject;
import registationTest.RegistrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTest.class,
        RegistrationTest.class,
        ApiTests.class
})
public class Smoke {
}
