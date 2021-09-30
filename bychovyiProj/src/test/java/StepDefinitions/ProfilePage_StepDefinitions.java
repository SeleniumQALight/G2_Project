package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.ProfilePage;

import static libs.DriverHelper.getWebDriver;

public class ProfilePage_StepDefinitions {
    private ProfilePage profilePage = new ProfilePage(getWebDriver());

    @Then("^User is redirect to Profile page$")
    public void userIsRedirectToProfilePage() {
        profilePage.checkIsRedirectToProfilePage();
    }

    @And("^User sees (\\d+) posts in Post list on Profile page$")
    public void userSeesPostsInPostListOnProfilePage(int expectedNumberOfPosts) {
        profilePage.checkIsNumberOfPosts(expectedNumberOfPosts);
    }
}
