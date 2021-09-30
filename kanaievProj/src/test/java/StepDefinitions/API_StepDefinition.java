package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.And;
import libs.TestData;
import libs.Util;

public class API_StepDefinition {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @And("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.PASHA_USER_NAME;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.PASHA_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost("Post from API " + i + " " + Util.getDateAndTimeFormatted(), userName, password);
        }
    }
}
