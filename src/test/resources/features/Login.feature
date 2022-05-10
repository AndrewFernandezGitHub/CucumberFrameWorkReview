#Author: AndrewF@companyemail.com
@tag @login
Feature: Login Feature Credentials

  Background:
    Given user is navigated to HRMS application

  @tag1 @smoke @ui @sprint1
  Scenario: Valid admin login
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in

  Scenario Outline:Invalid login
    When user enters "<username>" and "<password>"
    And user clicks on login button
    Then user see "<errorMessage>" on the screen
    Examples:
    |username|password|errorMessage|
    |Admin   |Hum |Invalid credentials|
    |Admin   |        |Password cannot be empty|
    |        |Hum@nhrm123 |Username cannot be empty|


