package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.And;
import libs.TestData;
import libs.Util;

public class API_StepDefinition {
    final String DEFAULT="default";
    private ApiHelper apiHelper=new ApiHelper();

    @And("Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts,String userName, String passWord) {
if (DEFAULT.equalsIgnoreCase(userName)){
    userName= TestData.VALID_LOGIN;
}
if (DEFAULT.equalsIgnoreCase(passWord)){
    passWord=TestData.VALID_PASSWORD;
}
for (int i = 0; i < numberOfPosts; i++) {
    apiHelper.createPost("Post from API " + i + " " + Util.getDateAndTimeFormatted(), userName, passWord);
        }
    }
}