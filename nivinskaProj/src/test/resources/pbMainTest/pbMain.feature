@PbMainTest @FullRegression
Feature: Pb currency exchange feature

  @R001
  Scenario Outline: R001 Login with invalid Login
    Given Get '<currency>' current exchange via API
    When User opens 'PbMain' page
    And Get '<currency>' current exchange from website
    Then Compare current exchange via API and from website


    Examples:
      | currency |
      | USD      |
      | EUR      |
      | RUR      |
      | BTC      |