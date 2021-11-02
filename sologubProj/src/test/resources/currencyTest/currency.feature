@CurrencyTest @FullRegression
Feature: Currency

  Background:
    Given User opens 'Privatbank' page

  @R005
  Scenario Outline: R005 Check currency
    Given get 'buy' value for '<currency>' by API
    And get 'sale' value for '<currency>' by API
    And get 'buy' value for '<currency>' in 'PrivatBank' website
    And get 'sale' value for '<currency>' in 'PrivatBank' website
    Then compare currency 'buy' values received by API and in 'PrivatBank' website
    And compare currency 'sale' values received by API and in 'PrivatBank' website

    Examples:
      | currency|
      | USD |
      | EUR |
      | RUR |






