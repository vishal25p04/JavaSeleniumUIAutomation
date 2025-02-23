Feature: To test Saucedemo Login Page

    Background:
      Given An user loads the URL

  @Login
Scenario: User logins to Saucedemo and validate homePage
    When User login to the page
    Then User enters the Product page

  @AddToCart
  Scenario: User add the items to cart and details in Information page
#    LoginPageStepDef
    When User login to the page
#    ProductPageStepDef
    Then User enters the Product page
    And User add the items to cart
#    CartPageStepDef
    And User inside the Cart page
#    InformationPageStepDef
    And User enters the Information page
#    OverviewAndFinishPageStepDef
    And User validates the Overview page
    And User validates the Finish page

