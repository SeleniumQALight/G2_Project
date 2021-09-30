package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.ProfilePage;

public class ProfilePage_StepDefinition {

    private ProfilePage profilePage;

    @Then("^User is redirect to Profile page$")
        public void UserIsRedirectToProfilePage() {
            profilePage.checkIsRedirectToProfilePage();
        }

    @And("^User sees (\\d+) posts in Posts list on Profile page$")
    public void UserSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        profilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
