package page;

import com.aventstack.extentreports.Status;
import hooks.Hooks;
import org.apache.logging.log4j.Logger;
import utils.ExtentReportManager;
import utils.PageActionUtils;
import webElements.LoginPageElements;

public class LoginPage extends PageActionUtils {

    public Logger logger;

    public LoginPage(){
        this.logger= Hooks.logger;
    }

    public void openLoginPage(){
        logger.info("Opening Login page");
        getURL(getKeyFromPropertyConfigReader("URL"));
        ExtentReportManager.createTest("Navigate to Login Page").log(Status.INFO, "Navigating to the login page");
        validatePageTitle("Swag Labs");
        logger.info("Inside Login page");
    }

    public void enterLoginPageDetails(){
        waitForElementToBeVisible(LoginPageElements.userName);
        typeValueForElement(LoginPageElements.userName,getDecryptedValue("Username"));
        waitForElementToBeVisible(LoginPageElements.password);
        typeValueForElement(LoginPageElements.password,getDecryptedValue("Password"));
        waitForElementToBeVisible(LoginPageElements.loginButton);
        clickElement(LoginPageElements.loginButton);
    }
}
