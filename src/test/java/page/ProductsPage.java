package page;

import hooks.Hooks;
import org.apache.logging.log4j.Logger;
import utils.PageActionUtils;
import webElements.ProductsPageElements;

public class ProductsPage extends PageActionUtils {

    private final Logger logger;

    public ProductsPage(){
        this.logger= Hooks.logger;
    }

    public void openProductsPage(){
        validateElementText(ProductsPageElements.products,"Products");
        logger.info("Inside Products page");
    }
}
