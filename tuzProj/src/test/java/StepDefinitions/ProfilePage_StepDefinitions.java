package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.ProfilePage;

public class ProfilePage_StepDefinitions {
    private ProfilePage profilePage = new ProfilePage(DriverHelper.getWebDriver());

    @Then("^User is redirect to Profile page$")
    public void userIsRedirectToProfilePage() {
        profilePage.checkIsRedirectToProfilePage();
    }

    @And("^User ses (\\d+) posts in Posts list on Profile page$")
    public void userSesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        profilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
