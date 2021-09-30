package StepDefinitions;



import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfilePage_StepDefinition {
    private LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    private HomePage homePage = new HomePage(DriverHelper.getWebDriver());
    private ProfilePage profilePage = new ProfilePage(DriverHelper.getWebDriver());


    @Then("^User is redirect to Profile page$")
    public void userIsRedirectToProfilePage() {
        profilePage.checkIsRedirectToProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts list on Profile page$")
    public void userSeesPostsInPostsListOnProfilePage(int expectedNumberOfPosts) {
        profilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
