package stepDef;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;


public class saucedemoPage {

    private final WebDriver driver;
    private final Logger logger;

    public saucedemoPage(){
        this.driver= Hooks.driver;
        this.logger=Hooks.logger;
    }

    @Given("An user opens browser and load the URL")
    public void an_user_opens_browser_and_load_the_url() {
        logger.info("Opening page");
        driver.get("https://www.saucedemo.com/v1/index.html");
        logger.info("Validating Login Page title");
        String loginTitle = driver.getTitle();
        Assert.assertEquals("Swag Labs",loginTitle);
        logger.info("Title validated successfully");

    }
    @When("User login to the page")
    public void user_login_to_the_page() throws InterruptedException {
        logger.info("Entering credentials");
        driver.findElement(By.xpath("//input[@id='user-name']")).
                sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).
                sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        logger.info("Credentials Entered");
    }
    @And("User enters the home page")
    public void user_enters_the_home_page() {
        logger.info("Validating HomePage");
        String products=driver.findElement(By.xpath("//div[@class='product_label']"))
                .getText();
        Assert.assertEquals("Products",products);
        logger.info("Inside HomePage");
    }
}
