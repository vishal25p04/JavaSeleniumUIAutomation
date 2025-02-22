package page;

import hooks.Hooks;
import org.apache.logging.log4j.Logger;
import utils.PageActionUtils;
import webElements.LoginPageElements;

public class LoginPage extends PageActionUtils {

    private final Logger logger;

    public LoginPage(){
        this.logger= Hooks.logger;
    }

    public void openLoginPage(){
        logger.info("Opening Login page");
        getURL("https://www.saucedemo.com/v1/index.html");
        validatePageTitle("Swag Labs");
        logger.info("Inside Login page");
    }

    public void enterLoginPageDetails(){
        typeValueForElement(LoginPageElements.userName,"standard_user");
        typeValueForElement(LoginPageElements.password,"secret_sauce");
        clickElement(LoginPageElements.loginButton);
    }
}
