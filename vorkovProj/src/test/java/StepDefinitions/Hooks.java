package StepDefinitions;


import api.ApiHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverHelper;
import org.apache.log4j.Logger;

import java.util.Locale;

import static libs.TestData.VALID_LOGIN;
import static libs.TestData.VALID_PASSWORD;

public class Hooks {
    private DriverHelper driverHelper = new DriverHelper();
    private ApiHelper apiHelper = new ApiHelper();
    private Logger logger = Logger.getLogger(getClass());

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        logger.info(scenario.getName() + " was started ");
        driverHelper.createDriver();
    }

    @Before(value = "@BeforeDeletingAllPostsForDefaultUser", order = 100)
    public void deletingAllPostsForDefaultUser() {
        apiHelper.deletePostsTillPresent(VALID_LOGIN, VALID_PASSWORD);
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        driverHelper.closeDriver();
        logger.info(scenario.getName() + " was ended with status " + scenario.getStatus().toUpperCase(Locale.ROOT));
    }

    @After(value = "@AfterDeletingAllPostsForDefaultUser", order = 50)
    public void deletingAllPostsAfterForDefaultUser() {
        apiHelper.deletePostsTillPresent(VALID_LOGIN, VALID_PASSWORD);
    }
}
