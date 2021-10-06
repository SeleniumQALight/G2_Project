@PbMainTest @FullRegression
Feature: Pb currency exchange feature

  @R009
  Scenario Outline: R009 Pb currency exchange
    Given Get '<currency>' current exchange via API
    When User opens 'PbMain' page
    And Get '<currency>' current exchange from website
    Then Compare current currency exchange via API and from website

    Examples:
      | currency |
      | USD      |
      | EUR      |
      | RUR      |