Feature: To test Saucedemo Login Page

  @Login
Scenario: User logins to Saucedemo and validate homePage
    Given An user opens browser and load the URL
    When User login to the page
    And User enters the home page