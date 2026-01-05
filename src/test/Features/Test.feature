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
    And User add the items "Sauce Labs Backpack" to cart
#    CartPageStepDef
    And User validate the items "Sauce Labs Backpack" inside Cart page
#    InformationPageStepDef
    And User enters the Information page
#    OverviewAndFinishPageStepDef
    And User validates the Overview page
    And User validates the Finish page

@AddToCartAndValidate
  Scenario Outline: User adds items to cart and validates them inside Cart Page
    When User login to the page
    Then User enters the Product page
    And User add the items "<itemName>" to cart
    And User validate the items "<itemName>" inside Cart page
    And User Click on Checkout button
     And User enters the Information page And enters "<FirstName>" "<LastName>" "<PostalCode>"

    Examples:
      | itemName                        | FirstName | LastName | PostalCode |
      | Sauce Labs Backpack             | abcd      | efgh     | 12345      |
      | Sauce Labs Bike Light           | gdef      | ijkl     | 67890      |
      | Sauce Labs Bolt T-Shirt         | mnop      | qrst     | 11223      |
      | Sauce Labs Fleece Jacket        | uvwx      | yzab     | 44556      |
      | Sauce Labs Onesie               | cdef      | ghij     | 77889      |
      | Test.allTheThings() T-Shirt (Red) | klmn     | opqr     | 99001     |

  @ExcelDataDriven @AddToCartAndValidateExcel @ScenarioId=TC_01_SLBP @test
    Scenario: User adds item1 to cart and validates them inside Cart Page using Excel DataDriven
      When User login to the page
      Then User enters the Product page
      And User add the items from Excel to cart
      And User validate the items from Excel inside Cart page
      And User Click on Checkout button
      And User enters the Information page And enters data from Excel

  @ExcelDataDriven @AddToCartAndValidateExcel @ScenarioId=TC_01_SLBP_C2
  Scenario: User adds item1 to cart and validates them inside Cart Page using Excel DataDriven with Customer 2 details
    When User login to the page
    Then User enters the Product page
    And User add the items from Excel to cart
    And User validate the items from Excel inside Cart page
    And User Click on Checkout button
    And User enters the Information page And enters data from Excel

  @ExcelDataDriven @AddToCartAndValidateExcel @ScenarioId=TC_02_SLBL
  Scenario: User adds item2 to cart and validates them inside Cart Page using Excel DataDriven
    When User login to the page
    Then User enters the Product page
    And User add the items from Excel to cart
    And User validate the items from Excel inside Cart page
    And User Click on Checkout button
    And User enters the Information page And enters data from Excel

  @ExcelDataDriven @AddToCartAndValidateExcel @ScenarioId=TC_03_SLBTS
  Scenario: User adds item3 to cart and validates them inside Cart Page using Excel DataDriven
    When User login to the page
    Then User enters the Product page
    And User add the items from Excel to cart
    And User validate the items from Excel inside Cart page
    And User Click on Checkout button
    And User enters the Information page And enters data from Excel

  @ExcelDataDriven @AddToCartAndValidateExcel @ScenarioId=TC_04_SLFJ
  Scenario: User adds item4 to cart and validates them inside Cart Page using Excel DataDriven
    When User login to the page
    Then User enters the Product page
    And User add the items from Excel to cart
    And User validate the items from Excel inside Cart page
    And User Click on Checkout button
    And User enters the Information page And enters data from Excel

  @ExcelDataDriven @AddToCartAndValidateExcel @ScenarioId=TC_05_SLOP @test
  Scenario: User adds item5 to cart and validates them inside Cart Page using Excel DataDriven
    When User login to the page
    Then User enters the Product page
    And User add the items from Excel to cart
    And User validate the items from Excel inside Cart page
    And User Click on Checkout button
    And User enters the Information page And enters data from Excel

  @ExcelDataDriven @AddToCartAndValidateExcel @ScenarioId=TC_06_TTTTSRP @test
  Scenario: User adds item6 to cart and validates them inside Cart Page using Excel DataDriven
    When User login to the page
    Then User enters the Product page
    And User add the items from Excel to cart
    And User validate the items from Excel inside Cart page
    And User Click on Checkout button
    And User enters the Information page And enters data from Excel
