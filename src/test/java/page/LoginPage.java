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
        getURL(getKeyFromPropertyConfigReader("URL"));
        validatePageTitle("Swag Labs");
        logger.info("Inside Login page");
    }

    public void enterLoginPageDetails(){
        typeValueForElement(LoginPageElements.userName,getDecryptedValue("Username"));
        typeValueForElement(LoginPageElements.password,getDecryptedValue("Password"));
        clickElement(LoginPageElements.loginButton);
    }
}
