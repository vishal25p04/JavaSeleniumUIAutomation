package utils;

import hooks.Hooks;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;

public class PageActionUtils extends ExcelUtils {
    //private final WebDriver driver;
    private final Logger logger;

    public PageActionUtils(){
        //this.driver= Hooks.driver;
        this.logger=Hooks.logger;

    }

    public void typeValueForElement(String locator,String value){
        logger.info("Entering value for locator: "+locator);
        DriverFactory.getDriver().findElement(By.xpath(locator)).sendKeys(value);
    }

    public void clickElement(String locator){
        logger.info("Clicking on locator: "+locator);
        DriverFactory.getDriver().findElement(By.xpath(locator)).click();
    }

    public void getURL(String url){
        DriverFactory.getDriver().get(url);
    }

    public void validatePageTitle(String title){
        logger.info("Validating Page title");
        String loginTitle = DriverFactory.getDriver().getTitle();
        Assert.assertEquals(title,loginTitle);
        logger.info("Title validated successfully");
    }

    public void validateElementText(String locator, String expectedText){
        logger.info("Validating element text");
        String actualText = DriverFactory.getDriver().findElement(By.xpath(locator)).getText();
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

    public void  waitForElementToBeVisible(String locator){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(45));
        logger.info("Waiting for element to be clickable: " + locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public boolean isElementDisplayed(String locator){
        try {
            //waitForElementToBeClickable(locator);
            return DriverFactory.getDriver().findElement(By.xpath(locator)).isDisplayed();
        } catch (Exception e) {
            logger.error("Element not found: " + locator);
            return false;
        }
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String relativePath = "screenshots/" + screenshotName + ".png"; // Relative path
            String absolutePath = "target/ExtentReports/" + relativePath; // Save under ExtentReports folder
            File destination = new File(absolutePath);
            FileUtils.copyFile(source, destination);
            return relativePath; // Return relative path for Extent Report
        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }


}
