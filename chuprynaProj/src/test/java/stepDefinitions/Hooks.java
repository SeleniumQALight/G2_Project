package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;

public class Hooks {

    private DriverHelper driverHelper = new DriverHelper();

    //  (!) note that import NOT from JUnit
    @Before
    public void setUp() {
        driverHelper.createDriver();
    }

    @After
    public void tearDown() {
        driverHelper.closeDriver();
    }
}
