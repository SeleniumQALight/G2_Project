@PostsTest @FullRegression
Feature: currencyRate feature

  @R010
  Scenario: R003 Currency rate check
    Given User opens 'Main' page
    And  Create 2 new posts via API for 'default' user and 'default' password
    When  User click on 'Profile' button on 'Home' page
    Then User is redirect to Profile page
    And User sees 2 post in Post list on Profile page


