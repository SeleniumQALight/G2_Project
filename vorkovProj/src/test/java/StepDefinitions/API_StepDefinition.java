package StepDefinitions;


import api.ApiHelper;
import cucumber.api.java.en.And;
import libs.TestData;

public class API_StepDefinition {

    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @And("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String username, String password) {
        if (DEFAULT.equalsIgnoreCase(username)) {
            username = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPosts("Post from API " + i, username, password);
        }
    }
}
