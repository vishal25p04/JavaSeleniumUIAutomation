package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"@target/rerun.txt"},
        glue = {"stepDef","hooks"},
        tags = "@ExcelDataDriven",
        monochrome = true,
        plugin = {"pretty","html:target/HtmlReports/rerun-report.html"}
)

public class RerunFailedTestRunner
        //extends AbstractTestNGCucumberTests
{

//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}
