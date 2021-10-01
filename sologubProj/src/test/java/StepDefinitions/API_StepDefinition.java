package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.And;
import libs.DriverHelper;
import libs.TestData;
import pages.LoginPage;

public class API_StepDefinition {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @And("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN;
        }

        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD;
        }

        for (int i = 0; i < numberOfPosts ; i++) {
            apiHelper.createPost("Post from API" + i, userName, password);

        }
    }
}
