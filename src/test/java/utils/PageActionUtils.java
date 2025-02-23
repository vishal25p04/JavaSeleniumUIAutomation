package utils;

import hooks.Hooks;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

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

    public String getKeyFromPropertyConfigReader(String key){
        try{
            Properties properties = new Properties();
            String filePath = "src/test/resources/Config.properties";
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
            return properties.getProperty(key);
        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDecryptedValue(String key){
        String encryptedValue =  getKeyFromPropertyConfigReader(key);
        byte[] decryptedByte=  Base64.getDecoder().decode(encryptedValue);
        return new String(decryptedByte);
    }


}
