package stepDef;

import hooks.Hooks;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.Logger;
import page.CheckoutYourInformationPage;

import java.io.IOException;

public class InformationPageStepDef {
    private final Logger logger;
    private final CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();

    public InformationPageStepDef() {
        this.logger = Hooks.logger;
    }

    @And("User enters the Information page And enters {string} {string} {string}")
    public void userEntersTheInformationPageAndEnters(String arg0, String arg1, String arg2) {
        // Implementation for entering information on the Information page
        logger.info("Entering information on the Information page");
        checkoutYourInformationPage.enterInformation(arg0, arg1, arg2);
        checkoutYourInformationPage.clickOnContinue();
    }

    @And("User enters the Information page And enters data from Excel")
    public void userEntersTheInformationPageAndEntersDataFromExcel() throws IOException {
        logger.info("Entering information from Excel on the Information page");
        checkoutYourInformationPage.enterInformationFromExcel();
        checkoutYourInformationPage.clickOnContinue();
    }
}
