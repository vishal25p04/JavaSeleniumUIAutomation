package hooks;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.DriverFactory;
import utils.ExcelReportUtils;
import utils.ExtentReportManager;
import utils.PageActionUtils;
import java.io.IOException;
import java.time.Duration;

public class Hooks extends PageActionUtils {

    public static WebDriver driver;
    public static Logger logger;
    public static String scenarioId;
    private static ExcelReportUtils excelReport = new ExcelReportUtils();
    private static boolean headerCreated = false;
    private static final String REPORT_PATH = "target/ExtentReports/ExtentReport.html";

    @Before(order=0)
    public void setUp(){
        logger = LogManager.getLogger(Hooks.class);
        System.setProperty("webdriver.edge.driver","src/test/resources/msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("remote-allow-origins=*");
        options.addArguments("--headless");

        logger.info("Initializing Webdriver");
        driver =new EdgeDriver(options);
        DriverFactory.setDriver(driver);

        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Before(order=1)
    public void beforeScenario(Scenario scenario) {
        // Retrieve the Scenario ID from the tags
        scenarioId = scenario.getSourceTagNames()
                .stream()
                .filter(tag -> tag.startsWith("@ScenarioId="))
                .map(tag -> tag.replace("@ScenarioId=", ""))
                .findFirst()
                .orElse(null);
    }

    @Before(order=2)
    public void beforeScenarioSetExtentReportManager(Scenario scenario) {
        ExtentReportManager.getInstance(REPORT_PATH);
        //ExtentReportManager.createTest(scenario.getName());
    }

    @After(order=0)
    public void afterScenario(Scenario scenario) throws IOException {
        if(scenarioId!=null){
            logger.info("Completed Scenario ID: " + scenarioId);
            int rowCount = getRowCount("src/test/resources/testData.xlsx", "Sheet1");
            for (int i = 0; i <= rowCount; i++) {
                String testCaseId = getCellData("src/test/resources/testData.xlsx", "Sheet1", i, 0); // 1st column contains ScenarioIds
                if (scenarioId.equals(testCaseId)) {
                    logger.info("Adding testcase status to Excel for Scenario ID: " + scenarioId);
                    if(scenario.isFailed()) {
                        setCellData("src/test/resources/testData.xlsx", "Sheet1", i, 5, "FAIL"); // 6th column for Status
                        fillRedColor("src/test/resources/testData.xlsx","Sheet1",i,5);
                    }
                    else {
                        setCellData("src/test/resources/testData.xlsx", "Sheet1", i, 5, "PASS"); // 6th column for Status
                        fillGreenColor("src/test/resources/testData.xlsx","Sheet1",i,5);
                    }
                }
            }
        }
    }

    @After(order=1)
    public void logScenarioInExcel(Scenario scenario) throws IOException {
        if (!headerCreated) { // Check if the header is already created
            excelReport.createHeader();
            headerCreated = true; // Set the flag to true after creating the header
        }
        excelReport.logScenario(scenario);
    }

    @After(order=2)
    public void addScenarioInExtentReport(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenShotPath= captureScreenshot(DriverFactory.getDriver(), scenario.getName().replaceAll(" ", "_"));
            assert screenShotPath != null; // Prevent potential NullPointerException
            ExtentReportManager.createTest(scenario.getName()).fail("Scenario failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
        } else {
            ExtentReportManager.createTest(scenario.getName()).pass("Scenario passed");
        }
        ExtentReportManager.flush();
    }

    @After(order=3)
    public void tearDown(Scenario scenario){
        if(!scenario.isFailed()){ // To capture screenshot - if we quit driver, screenshot won't be captured
            //driver.quit();
            DriverFactory.quitDriver();
        }
        logger.info("Browser closed");
    }

    @AfterAll
    public static void afterAll() {
        excelReport.saveReport("target/ScenarioReport.xlsx");
    }



}
