package page;

import hooks.Hooks;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import utils.PageActionUtils;
import webElements.CheckoutYourInformationPageLocator;

import java.io.IOException;

public class CheckoutYourInformationPage extends PageActionUtils {

    private final Logger logger;
    private final String scenarioId;

    public CheckoutYourInformationPage() {
        this.logger = Hooks.logger;
        this.scenarioId = Hooks.scenarioId;
    }

    public void enterInformation(String firstName, String lastName, String postalCode) {
        logger.info("Entering customer information: " + firstName + " " + lastName + " " + postalCode);
        typeValueForElement(CheckoutYourInformationPageLocator.firstNameField, firstName);
        typeValueForElement(CheckoutYourInformationPageLocator.lastNameField, lastName);
        typeValueForElement(CheckoutYourInformationPageLocator.postalCodeField, postalCode);
    }

    public void clickOnContinue() {
        logger.info("Clicking on Continue button");
        clickElement(CheckoutYourInformationPageLocator.continueButton);
        if(!isElementDisplayed(CheckoutYourInformationPageLocator.checkoutOverviewPage)){
            Assert.fail("Customer Information is not filled correctly.");
        }
    }

    public void enterInformationFromExcel() throws IOException {
        logger.info("Entering customer information from Excel");
        // Implementation for entering information from Excel based on Scenario

        if (scenarioId != null) {
            logger.info("Processing Scenario ID: " + scenarioId);
            int rowCount = getRowCount("src/test/resources/testData.xlsx", "Sheet1");

            for (int i = 0; i <= rowCount; i++) {
                String testCaseId = getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 0); // 1st column contains ScenarioIds
                if (scenarioId.equals(testCaseId)) {

                    String firstName = getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 2); // 2nd column contains firstName
                    typeValueForElement(CheckoutYourInformationPageLocator.firstNameField, firstName);

                    String lastName = getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 3); // 3rd column contains lastName
                    typeValueForElement(CheckoutYourInformationPageLocator.lastNameField, lastName);

                    String postalCode = getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 4); // 4th column contains postalCode
                    typeValueForElement(CheckoutYourInformationPageLocator.postalCodeField, postalCode);
                }
            }
        } else {
            logger.error("Scenario ID not found in tags.");
            Assert.fail("Scenario ID not found in tags.");
        }
    }
}
