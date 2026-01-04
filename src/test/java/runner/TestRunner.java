package runner;

import io.cucumber.junit.Cucumber;
//import io.cucumber.testng.CucumberOptions;
import io.cucumber.junit.CucumberOptions;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/Features/Test.feature"},
        glue = {"stepDef","hooks"},
        tags = "@ExcelDataDriven",
        monochrome = true,
        plugin = {"pretty","html:target/HtmlReports/report.html","json:target/cucumber.json" }
)

public class TestRunner
        //extends AbstractTestNGCucumberTests
{

//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}
