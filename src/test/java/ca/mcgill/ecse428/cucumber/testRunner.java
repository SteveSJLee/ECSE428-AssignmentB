package ca.mcgill.ecse428.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
 
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources",
				junit = "--step-notifications",
				plugin = {"pretty", "html:target/cucumber"},
				tags = "not @alternative"
				)
public class testRunner {
	//"@Normal", "@Alternative", "@Error"
 
}