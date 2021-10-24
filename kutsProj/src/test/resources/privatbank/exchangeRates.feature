Feature: Exchange rates in PrivatBank

Background:
  Given User opens 'PrivatBankHome' page

  @R004
  Scenario Outline: R004 Check exchange rates in PrivatBank <ccy>
    And User performs scroll to 'Exchange rates' block
    When User selects 'In branches' in dropdown
    Then User sees correct exchange rates for '<ccy>'

    Examples:
      | ccy |
      | USD |
      | EUR |
      | RUR |