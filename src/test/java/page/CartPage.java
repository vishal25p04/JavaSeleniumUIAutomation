package page;

import hooks.Hooks;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import utils.PageActionUtils;
import webElements.CartPageLocator;

import java.io.IOException;

public class CartPage extends PageActionUtils {

    private final Logger logger;
    private final String scenarioId;

    public CartPage() {
        this.logger = Hooks.logger;
        this.scenarioId = Hooks.scenarioId;
    }

    public void validateItemsInCart(String itemName) {
        logger.info("Validating item: "+itemName+" in the Cart page");
        validateElementText(CartPageLocator.itemName(itemName), itemName);
    }

    public void clickOnCheckout(){
        waitForElementToBeVisible(CartPageLocator.checkoutButton);
        clickElement(CartPageLocator.checkoutButton);
    }

    public void validateItemsInCartFromExcel() throws IOException {
        logger.info("Validating items from Excel in the Cart page");

        if (scenarioId != null) {
            logger.info("Processing Scenario ID: " + scenarioId);
            int rowCount = getRowCount("src/test/resources/testData.xlsx", "Sheet1");

            for (int i = 0; i <= rowCount; i++) {
                String testCaseId = getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 0); // 1st column contains ScenarioIds
                if (scenarioId.equals(testCaseId)) {
                    String itemName = getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 1); // 2nd column contains ScenarioIds
                    validateElementText(CartPageLocator.itemName(itemName), itemName);
                }
            }
        } else {
            logger.error("Scenario ID not found in tags.");
            Assert.fail("Scenario ID not found in tags.");
        }
    }
}
