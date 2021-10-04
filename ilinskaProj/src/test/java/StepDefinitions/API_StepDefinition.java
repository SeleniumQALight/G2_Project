package StepDefinitions;

import static libs.DriverHelper.getWebDriver;

import api.ApiHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import libs.TestData;
import pages.LoginPage;

public class API_StepDefinition {
    final String Default = "default";
    private ApiHelper apiHelper= new ApiHelper();


    @And("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String passWord) {
    if (Default.equalsIgnoreCase(userName)){
        userName= TestData.VALID_LOGIN;
        }
        if (Default.equalsIgnoreCase(passWord)){
            passWord= TestData.VALID_PASSWORD;
        }
        for ( int i=0;i<numberOfPosts;i++){
            apiHelper.createPost("Post from API"+i,userName,passWord);
        }
    }
}