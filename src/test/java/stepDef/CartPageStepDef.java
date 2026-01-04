package stepDef;

import hooks.Hooks;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.Logger;
import page.CartPage;

import java.io.IOException;

public class CartPageStepDef {

    private final Logger logger;
    private final CartPage addToCartPage = new CartPage();

    public CartPageStepDef() {
        this.logger = Hooks.logger;
    }

    @And("User validate the items {string} inside Cart page")
    public void userValidateTheItemsInsideCartPage(String arg0) {
        logger.info("Validating items in the Cart page");
        addToCartPage.validateItemsInCart(arg0);
    }

    @And("User Click on Checkout button")
    public void userClickOnCheckoutButton() {
        logger.info("Clicking on Checkout button");
        addToCartPage.clickOnCheckout();
    }

    @And("User validate the items from Excel inside Cart page")
    public void userValidateTheItemsFromExcelInsideCartPage() throws IOException {
        logger.info("Validating items from Excel in the Cart page");
        addToCartPage.validateItemsInCartFromExcel();
    }
}
