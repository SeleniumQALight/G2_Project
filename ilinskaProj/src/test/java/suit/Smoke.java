package suit;

import login.LoginTest;
import login.LoginTestWithPageObject;
import login.Login_InvCrd;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registration.Registration;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        Registration.class
})

public class Smoke {
}
