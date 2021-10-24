package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PrivatBankHomePage;

import static libs.DriverHelper.getWebDriver;

public class PrivatBankHomePage_StepDefinition {

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
        privatBankHomePage.getAllExchangeRatesViaAPI();
    }

    @Then("^User sees correct exchange rates for '(.*)'$")
    public void user_sees_correct_exchange_rates_for_currency(String currency) {
        privatBankHomePage.checkExchangeRatesForCurrency(currency);
    }
}
