package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class Hooks {

    public static WebDriver driver;
    public static Logger logger;

    @Before
    public void setUp(){
        logger = LogManager.getLogger(Hooks.class);
        System.setProperty("webdriver.edge.driver","src/test/resources/msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("remote-allow-origins=*");
        logger.info("Initializing Webdriver");
        driver =new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @After
    public void tearDown(){
        driver.quit();
        logger.info("Browser closed");
    }

}
