@LoginTest @FullRegression
Feature: User Login


  @R002
  Scenario Outline: R002 Login with valid Login
    Given User opens 'Login' page
    When User enters 'default' login into 'Login' input on 'Login' page
    And User enters 'default' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees 'SignOut' button on 'Home' page



