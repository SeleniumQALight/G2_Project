package suits;

import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTest.RegistrationTest;

@RunWith(Suite.class)
// список классов с тестами для запуска
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTest.class
})
public class Smoke {
}
