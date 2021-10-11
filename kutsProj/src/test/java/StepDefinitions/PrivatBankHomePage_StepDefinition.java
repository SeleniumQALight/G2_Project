package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import pages.PrivatBankHomePage;

import static libs.DriverHelper.getWebDriver;

public class PrivatBankHomePage_StepDefinition {
    Logger logger = Logger.getLogger(getClass());

    private PrivatBankHomePage privatBankHomePage = new PrivatBankHomePage(getWebDriver());

    @Given("^User opens 'PrivatBankHome' page$")
    public void givenUserOpensPrivatBankHomePage() {
        privatBankHomePage.openPrivatBankHomePage();
    }

    @And("^User performs scroll to 'Exchange rates' block$")
    public void user_performs_scroll_to_Exchange_rates_block() {
        privatBankHomePage.scrollToExchangeRatesBlock();
    }

    @When("^User selects 'In branches' in dropdown$")
    public void user_selects_In_branches_in_dropdown() {
        privatBankHomePage.selectInBranchesValueInDDCoursesType();
    }

    @Then("^User sees correct '(.*)' buying rate$")
    public void user_sees_correct_exchange_buying_rate(String currency) {
        privatBankHomePage.checkExchangeBuyingRate(currency);
    }

    @And("^Users sees correct '(.*)' selling rate$")
    public void users_sees_correct_exchange_selling_rate(String currency) {
        privatBankHomePage.checkExchangeSellingRate(currency);
    }
}
