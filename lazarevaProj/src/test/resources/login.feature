@LoginTest @FullRegression
Feature: User Login

Background:
  Given User opens 'Login' page

  @R001
  Scenario Outline: R001 Login with invalid Login
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<passWord>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / password'

    Examples:
      | login       | passWord     |
      | Wrong login | Wrong pass   |


  @R002
  Scenario Outline: R002 Login with valid Login
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<passWord>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees 'Sign Out' button on 'Home' page

    Examples:
      | login       | passWord       |
      | testiren    | qwerty12345678 |