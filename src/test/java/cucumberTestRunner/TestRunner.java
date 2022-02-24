package cucumberTestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/Features", 
glue = "com.cucumber.framework.stepdefinition.login", 
		monochrome = true,
		plugin = {"html:target/cucumber-html-report"}
		)

public class TestRunner {

}


