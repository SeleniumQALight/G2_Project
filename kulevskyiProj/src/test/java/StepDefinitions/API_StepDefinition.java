package StepDefinitions;

import api.ApiHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import libs.TestData;
import org.junit.Test;
import pages.LoginPage;

import static libs.DriverHelper.getWebDriver;

public class API_StepDefinition {
    final String DEFFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @And("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String passWord) {
        if (DEFFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN;
        }
        if (DEFFAULT.equalsIgnoreCase(passWord)){
            passWord = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost("Post from API" + i, userName, passWord);

        }
    }
}
