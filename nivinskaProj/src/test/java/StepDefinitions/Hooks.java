package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import libs.DriverHelper;

public class Hooks {

    private DriverHelper driverHelper = new DriverHelper();

    @Before
    public void setUp() {
        driverHelper.createDriver();
    }

    @After
    public void teatDown() {
        driverHelper.closeDriver();
    }
}
