package suits;

import apiTests.ApiTest;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTest.RegistrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTest.class,
        ApiTest.class
})
public class Smoke {

}
