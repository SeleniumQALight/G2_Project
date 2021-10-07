@PBTest @Exam
Feature: Currency Course PrivatBank


  @PB001
  Scenario Outline: PB001 Check currency course PrivatBank
    Given User gets '<Currency>' course from PrivatBank API
    When User opens PrivatBank Lending Page
    Then User gets '<Currency>' course from Lending Page and compare it with API data


    Examples:
      | Currency |
      | USD      |
      | EUR      |
      | RUR      |
