package ca.mcgill.ecse428.cucumber;

import ca.mcgill.ecse428.email.Gmail;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailStepDefinitions {
	
	Gmail gmail;
    String email = "ecse.428.test@gmail.com";
	String password = "asdf1234()_+";
	@Given("^I am on Gmail page$")
    public void givenOnGmailPage() throws Throwable {
		System.out.println("Given I am on Gmail page");
		gmail = new Gmail();
		//gmail.setWebdriver();
		//gmail.loginToGmail();
	}
	
	@When("^test when$")
    public void Test1() throws Throwable {
		System.out.println("When");
	}
	
	@Then("^test then$")
    public void Test2() throws Throwable {
		System.out.println("Then");
	}
    
}
