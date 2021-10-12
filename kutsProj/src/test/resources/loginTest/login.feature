@LoginTest @FullRegression
Feature: User Login


  @R001
  Scenario Outline: R001 Login with invalid Login <login>
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<passWord>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / password'


    Examples:
      | login       | passWord     |
      | Wrong login | Wrong pass   |
      |             | 123456qwerty |


  @R002
  Scenario: R002 Login with valid credentials
    Given User opens 'Login' page
    When User enters 'default' login into 'Login' input on 'Login' page
    And User enters 'default' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User is redirected on 'Home' page