package stepDef;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.Logger;
import page.ProductsPage;


public class ProductsPageStepDef {

    private final Logger logger;

    public ProductsPageStepDef(){
        this.logger=Hooks.logger;
    }

    @Then("User enters the Product page")
    public void user_enters_the_product_page() {
        logger.info("Validating ProductPage");
        ProductsPage productsPage = new ProductsPage();
        productsPage.openProductsPage();
    }
}
