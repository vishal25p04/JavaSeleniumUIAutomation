Feature: To test Saucedemo Login Page

  @Login
Scenario: User logins to Saucedemo and validate homePage
    Given An user loads the URL
    When User login to the page
    Then User enters the home page