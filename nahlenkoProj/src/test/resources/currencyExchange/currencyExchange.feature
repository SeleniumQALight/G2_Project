@currencyExchange @FullRegression
Feature: Currency exchange PrivatBank


  @R007
  Scenario Outline: R007 Currency exchange PrivatBank
    Given User gets '<Currency>' course on ApiPrivatTest page
    When User opens PrivatBankExchangePage
    Then User gets '<Currency>' exchange course from 'PrivatBank' site
    And User compare exchange course from 'PrivatBank' site and  ApiPrivatTest page

    Examples:

      | Currency |
      | USD      |
      | EUR      |
      | RUR      |
