package ca.mcgill.ecse428.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
 
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources",
				junit = "--step-notifications",
				plugin = {"pretty", "html:target/cucumber"},
				tags = "@all"
				)
public class testRunner {
	//"@normal", "@alternative", "@error", "@all"
 
}