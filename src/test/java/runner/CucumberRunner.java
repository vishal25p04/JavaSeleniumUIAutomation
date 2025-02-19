package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/Features/Test.feature"},
        glue = {"stepDef","hooks"},
        tags = "@Login",
        monochrome = true
)

public class CucumberRunner{

}
