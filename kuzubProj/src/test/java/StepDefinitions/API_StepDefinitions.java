package StepDefinitions;

import API.ApiHelper;
import cucumber.api.java.en.And;
import libs.TestData;

public class API_StepDefinitions {
   final String DEFAULT = "default";
   private ApiHelper apiHelper = new ApiHelper();

    @And("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String passWord) {
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(passWord)){
            passWord = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost("Post from API" + i, userName, passWord);

        }
    }
}


