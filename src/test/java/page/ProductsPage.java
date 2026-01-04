package page;

import hooks.Hooks;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import utils.PageActionUtils;
import webElements.ProductsPageElements;

import java.io.IOException;

public class ProductsPage extends PageActionUtils {

    private final Logger logger;
    private final String scenarioId;

    public ProductsPage(){
        this.logger= Hooks.logger;
        this.scenarioId = Hooks.scenarioId;
    }

    public void openProductsPage(){
        validateElementText(ProductsPageElements.products,"Products");
        logger.info("Inside Products page");
    }

    public void addItemsToCart(String itemName) {
        clickElement(ProductsPageElements.addToCartButton(itemName));
        logger.info("Item added to the Cart");
    }

    public void clickOnCart() {
        clickElement(ProductsPageElements.cartButton);
        logger.info("Inside Add to Cart page");
    }

    public void addItemsToCartFromExcel() throws IOException {
        logger.info("Adding items to cart from Excel");

        if (scenarioId != null) {
            logger.info("Processing Scenario ID: " + scenarioId);
            int rowCount = getRowCount("src/test/resources/testData.xlsx", "Sheet1");

            for (int i = 0; i <= rowCount; i++) {
                String testCaseId = getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 0); // 1st column contains ScenarioIds
                if (scenarioId.equals(testCaseId)) {
                    String itemName = getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 1); // 2nd column contains ItemNames
                    clickElement(ProductsPageElements.addToCartButton(itemName));
                }
            }
        } else {
            logger.error("Scenario ID not found in tags.");
            Assert.fail("Scenario ID not found in tags.");
        }

    }
}
