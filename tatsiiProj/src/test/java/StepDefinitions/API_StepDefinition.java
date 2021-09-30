package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.And;
import libs.TestData;

public class API_StepDefinition {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @And("^Create (\\d+) new posts via API for (.*) user and (.*) password$")
    public void createNewPostViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String UserName, String passWord) {
        String userName;
        if (DEFAULT.equalsIgnoreCase(UserName)){
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(passWord)) {
            passWord = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost("Post from API" + i, UserName, passWord);

        }
    }
}
