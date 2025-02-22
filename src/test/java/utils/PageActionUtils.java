package utils;

import hooks.Hooks;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageActionUtils {
    private final WebDriver driver;
    private final Logger logger;

    public PageActionUtils(){
        this.driver= Hooks.driver;
        this.logger=Hooks.logger;
    }

    public void typeValueForElement(String locator,String value){
        logger.info("Entering value for locator: "+locator);
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public void clickElement(String locator){
        logger.info("Clicking on locator: "+locator);
        driver.findElement(By.xpath(locator)).click();
    }

    public void getURL(String url){
        driver.get(url);
    }

    public void validatePageTitle(String title){
        logger.info("Validating Page title");
        String loginTitle = driver.getTitle();
        Assert.assertEquals(title,loginTitle);
        logger.info("Title validated successfully");
    }

    public void validateElementText(String locator, String expectedText){
        logger.info("Validating element text");
        String actualText = driver.findElement(By.xpath(locator)).getText();
        Assert.assertEquals(expectedText,actualText);
        logger.info("Element text validated successfully");
    }
}
