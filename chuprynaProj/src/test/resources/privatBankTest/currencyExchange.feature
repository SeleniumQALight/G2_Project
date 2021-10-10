@CurrencyExchangeTest @FullRegression
Feature: PrivatBank currency exchange rate


  @PB001
  Scenario Outline: PB001 Check currency exchange rate on PrivatBank site
    Given User gets '<currency>' exchange rate from API
    When User opens 'PrivatBank' website
    And User gets '<currency>' exchange rate from 'PrivatBank' website
    And User checks values on 'PrivatBank' website are equal to those received from API


    Examples:
      | currency |
#      | USD      |
      | EUR      |
      | RUB      |
      | RUS      |


