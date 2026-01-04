package stepDef;

import hooks.Hooks;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.Logger;
import page.ProductsPage;

import java.io.IOException;

public class ProductsPageStepDef {

    private final Logger logger;
    private final ProductsPage productsPage = new ProductsPage();

    public ProductsPageStepDef(){
        this.logger=Hooks.logger;
    }

    @Then("User enters the Product page")
    public void user_enters_the_product_page() {
        logger.info("Validating ProductPage");
        productsPage.openProductsPage();
    }

    @And("User add the items {string} to cart")
    public void userAddTheItemsToCartAndClickOnCart(String arg0) {
        logger.info("Adding items to cart");
        productsPage.addItemsToCart(arg0);
        productsPage.clickOnCart();
    }

    @And("User add the items from Excel to cart")
    public void userAddTheItemsFromExcelToCart() throws IOException {
        logger.info("Adding items from Excel to cart");
        productsPage.addItemsToCartFromExcel();
        productsPage.clickOnCart();
    }
}
