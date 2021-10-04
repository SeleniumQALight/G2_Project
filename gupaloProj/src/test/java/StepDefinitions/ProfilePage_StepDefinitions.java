package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfilePage_StepDefinitions {
    private ProfilePage profilePage = new ProfilePage(DriverHelper.getWebDriver());

    @Then("^User is redirect to Profile page$")
    public void userIsRedirectToProfilePage() {
        profilePage.checkIsRedirectToProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts lost on Profile page$")
    public void userSeesPostsInPostsLostOnProfilePage(int expectedNumberOfPosts) {
        profilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
