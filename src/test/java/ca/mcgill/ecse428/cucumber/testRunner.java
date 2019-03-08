package ca.mcgill.ecse428.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
 
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources",
				plugin = {"html:target/cucumber-html-report"}, 
				snippets = SnippetType.CAMELCASE
				)
public class testRunner {
 
}