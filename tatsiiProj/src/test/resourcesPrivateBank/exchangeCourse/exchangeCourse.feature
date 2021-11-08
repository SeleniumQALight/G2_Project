@ExchangeCourseTest
Feature: Compare UI and API PrivateBank exchange course

  @OneCurrency
  Scenario: Compare USD UAH
    Given Open PrivateBank API HomePage

    When Remember Exchange Course 'USD' and 'UAH' from API

    Given Open PrivateBank UI HomePage

    When Remember Exchange Course 'USD' and 'UAH' from UI

    Then Compare Course UI and API Course

  @TestCurrency
  Scenario: Test UI

    Given Open PrivateBank UI HomePage

