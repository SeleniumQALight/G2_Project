@ExchangeCourseTest
Feature: Compare UI and API PrivateBank exchange course

  @OneCurrency
  Scenario: Compare USD UAH
    Given Exchange Course 'USD' and 'UAH' API call
