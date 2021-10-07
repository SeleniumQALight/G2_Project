@LoginTest @FullRegression
Feature: User Login


  @R001
  Scenario Outline: R001 Login with invalid Login
    Given User opens 'Login' page
    When User enters login '<login>' into 'Login' input on 'Login' page
    And User enters passWord '<passWord>' into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / password'


    Examples:
      | login       | passWord     |
      | Wrong login | Wrong pass   |


  @R002
  Scenario: R002 Login with valid Login
    Given User opens 'Login' page
    When User enters login 'default' into 'Login' input on 'Login' page
    And User enters passWord 'default' into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User is redirected to 'Home' page