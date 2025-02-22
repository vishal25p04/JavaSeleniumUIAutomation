package stepDef;


import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import page.LoginPage;

public class LoginPageStepDef {

    public Logger logger;

    public LoginPageStepDef(){
        this.logger= Hooks.logger;
    }

    @Given("An user loads the URL")
    public void an_user_opens_browser_and_load_the_url() {
        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();

    }

    @When("User login to the page")
    public void user_login_to_the_page() {
        LoginPage loginPage = new LoginPage();
        logger.info("Entering credentials");
        loginPage.enterLoginPageDetails();
        logger.info("Credentials Entered");
    }
}

