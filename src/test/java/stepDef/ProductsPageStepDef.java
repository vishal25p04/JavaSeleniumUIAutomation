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

    @Then("User enters the home page")
    public void user_enters_the_home_page() {
        logger.info("Validating HomePage");
        ProductsPage productsPage = new ProductsPage();
        productsPage.openProductsPage();
    }
}
