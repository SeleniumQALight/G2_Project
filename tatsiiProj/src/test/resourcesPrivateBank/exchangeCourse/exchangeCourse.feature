@ExchangeCourseTest
Feature: Compare UI and API PrivateBank exchange course

  @OneCurrency
  Scenario: Compare USD UAH
    Given Open PrivateBank API HomePage

    When Remember Exchange Course 'RUR' and 'UAH' from API

    Given Open PrivateBank UI HomePage

    When Remember Exchange Course 'RUB' and 'UAH' from UI

    Then Compare Course UI and API Course

  @MultiCurrency
  Scenario Outline: Compare ccy baseCcy

    Given Open PrivateBank API HomePage

    When Remember Exchange Course '<ccy>' and '<baseCcy>' from API

    Given Open PrivateBank UI HomePage

    When Remember Exchange Course '<ccy2>' and '<baseCcy>' from UI

    Then Compare Course UI and API Course

    Examples:
      | ccy | ccy2 | baseCcy |
      | USD | USD  | UAH     |
      | EUR | EUR  | UAH     |
      | RUR | RUB  | UAH     |





